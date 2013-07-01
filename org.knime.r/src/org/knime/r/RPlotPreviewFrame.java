/*
 * ------------------------------------------------------------------------
 *
 *  Copyright (C) 2003 - 2013
 *  University of Konstanz, Germany and
 *  KNIME GmbH, Konstanz, Germany
 *  Website: http://www.knime.org; Email: contact@knime.org
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License, Version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 *  Additional permission under GNU GPL version 3 section 7:
 *
 *  KNIME interoperates with ECLIPSE solely via ECLIPSE's plug-in APIs.
 *  Hence, KNIME and ECLIPSE are both independent programs and are not
 *  derived from each other. Should, however, the interpretation of the
 *  GNU GPL Version 3 ("License") under any applicable laws result in
 *  KNIME and ECLIPSE being a combined program, KNIME GMBH herewith grants
 *  you the additional permission to use and propagate KNIME together with
 *  ECLIPSE with only the license terms in place for ECLIPSE applying to
 *  ECLIPSE and the GNU GPL Version 3 applying for KNIME, provided the
 *  license terms of ECLIPSE themselves allow for the respective use and
 *  propagation of ECLIPSE together with KNIME.
 *
 *  Additional permission relating to nodes for KNIME that extend the Node
 *  Extension (and in particular that are based on subclasses of NodeModel,
 *  NodeDialog, and NodeView) and that only interoperate with KNIME through
 *  standard APIs ("Nodes"):
 *  Nodes are deemed to be separate and independent programs and to not be
 *  covered works.  Notwithstanding anything to the contrary in the
 *  License, the License does not apply to Nodes, you are not required to
 *  license Nodes under the License, and you are granted a license to
 *  prepare and propagate Nodes, in each case even if such Nodes are
 *  propagated with or for interoperation with KNIME.  The owner of a Node
 *  may freely choose the license terms applicable to such Node, including
 *  when such Node is propagated with or for interoperation with KNIME.
 * ---------------------------------------------------------------------
 *
 */
package org.knime.r;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.knime.core.node.util.ImageViewPanel;
import org.knime.core.node.util.ImageViewPanel.ScaleType;
import org.knime.core.util.SimpleFileFilter;

/** Frame showing PNG preview plot. Watches the RController image file.
 * 
 * @author Bernd Wiswedel, KNIME.com, Zurich, Switzerland
 *
 */
final class RPlotPreviewFrame extends JDialog {
    
    private final ImageViewPanel m_plotPreviewPanel;
    private final JPanel m_cardLayoutPanel;
    private static final String NO_PLOT = "<no plot>";
    private static final String PLOT = "<plot>";
    
    public RPlotPreviewFrame(Frame parentFrame) {
        super(parentFrame, "R Plot", ModalityType.MODELESS);
        JPanel fullPanel = new JPanel(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        m_cardLayoutPanel = new JPanel(cardLayout);
        JPanel noPlotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        noPlotPanel.add(new JLabel(NO_PLOT));
        m_cardLayoutPanel.add(noPlotPanel, NO_PLOT);

        m_plotPreviewPanel = new ImageViewPanel();
        m_cardLayoutPanel.add(new JScrollPane(m_plotPreviewPanel), PLOT);
        fullPanel.add(m_cardLayoutPanel, BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });
        southPanel.add(closeButton);
        fullPanel.add(southPanel, BorderLayout.SOUTH);
        getContentPane().add(fullPanel);
    }
    
    private void onClose() {
        setVisible(false);
    }
    
    public void setSource(final File pngFile) throws IOException {
        BufferedImage image;
        CardLayout cardLayout= (CardLayout) m_cardLayoutPanel.getLayout();
        if (pngFile != null && pngFile.length() > 0) {
            try {
                image = ImageIO.read(pngFile);
            } catch (IOException e) {
                m_plotPreviewPanel.setImage(null);
                cardLayout.show(m_cardLayoutPanel, NO_PLOT);
                throw e;
            }
            m_plotPreviewPanel.setImage(image);
            cardLayout.show(m_cardLayoutPanel, PLOT);
        } else {
            m_plotPreviewPanel.setImage(null);
            cardLayout.show(m_cardLayoutPanel, NO_PLOT);
        }
    }
    
    public static void main(String[] args) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new SimpleFileFilter(".png", ".PNG"));
        int showOpenDialog = jFileChooser.showOpenDialog(null);
        if (showOpenDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }
        RPlotPreviewFrame rPlotPreviewFrame = new RPlotPreviewFrame(null);
        rPlotPreviewFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                System.exit(0);
            }
        });
        try {
            rPlotPreviewFrame.setSource(jFileChooser.getSelectedFile());
            rPlotPreviewFrame.m_plotPreviewPanel.setScaleType(ScaleType.None);
        } catch (IOException e) {
            e.printStackTrace();
        }
        rPlotPreviewFrame.pack();
        rPlotPreviewFrame.setVisible(true);
    }
}