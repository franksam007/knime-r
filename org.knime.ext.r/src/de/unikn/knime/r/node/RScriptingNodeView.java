/* 
 * -------------------------------------------------------------------
 * Copyright, 2003 - 2006
 * Universitaet Konstanz, Germany.
 * Lehrstuhl fuer Angewandte Informatik
 * Prof. Dr. Michael R. Berthold
 *
 * This file is part of the R integration plugin for KNIME.
 *
 * The R integration plugin is free software; you can redistribute 
 * it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation; either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St., Fifth Floor, Boston, MA 02110-1301, USA.
 * Or contact us: contact@knime.org.
 * -------------------------------------------------------------------
 * 
 */
package de.unikn.knime.r.node;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import org.rosuda.JRclient.REXP;
import org.rosuda.JRclient.RFileInputStream;
import org.rosuda.JRclient.RSrvException;

import de.unikn.knime.core.data.DataColumnSpec;
import de.unikn.knime.core.data.DataColumnSpecCreator;
import de.unikn.knime.core.data.DataTableSpec;
import de.unikn.knime.core.node.NodeLogger;
import de.unikn.knime.core.node.NodeModel;
import de.unikn.knime.core.node.NodeView;
import de.unikn.knime.core.node.util.DataColumnSpecListCellRenderer;

/**
 * <code>NodeView</code> and "RScripting" Node view.
 * 
 * @author Thomas Gabriel, Konstanz University
 */
public class RScriptingNodeView extends NodeView {

    private final JEditorPane m_shell;
    private final JTextArea m_output;
    
    private final JList m_list;
    private final DefaultListModel m_listModel;

    private static final String FILE_NAME = "bild.png";

    /** If smaller the picture is not displayed. */
    private static final int MIN_FILE_SIZE = 500;

    private static final NodeLogger LOGGER = NodeLogger
            .getLogger(RScriptingNodeView.class);

    /**
     * Creates a new view.
     * 
     * @param nodeModel The R Scripting model.
     */
    protected RScriptingNodeView(final NodeModel nodeModel) {
        super(nodeModel);
        // create output view
        m_output = new JTextArea();
        m_output.setFont(new Font("Courier", Font.PLAIN, 12));
        m_output.setEditable(false);
        m_output.setText("");
        // create shell
        m_shell = new JEditorPane();
        m_shell.setText("");
        m_shell.setFont(new Font("Courier", Font.PLAIN, 12));
        // m_shell.setWrapStyleWord(false);
        m_shell.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String[] text = m_shell.getText().split("\n");
                    if (text.length > 0) {
                        String cmd = text[text.length - 1];
                        cmd = cmd.replace('\n', ' ');
                        cmd = cmd.replace('\r', ' ');
                        cmd = cmd.replace('\t', ' ');
                        cmd = cmd.trim();
                        LOGGER.debug("eval: " + text[text.length - 1]);
                        try {
                            createPNG(); // has to be there before command is
                                            // send
                            REXP rexp = RNodeModel.getRconnection().eval(
                                    "try(" + cmd + ")");
                            print(rexp, cmd);
                            // m_shell.replaceSelection("\n");
                        } catch (RSrvException rse) {
                            m_output.append(
                                    RNodeModel.getRconnection().getLastError() 
                                    + "\n");
                        } finally {
                            m_shell.requestFocus();
                        }
                    }
                }
            }
        });
        // init column list
        m_listModel = new DefaultListModel();
        m_list = new JList(m_listModel);
        m_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_list.setCellRenderer(new DataColumnSpecListCellRenderer());
        m_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                Object o = m_list.getSelectedValue();
                if (o != null) {
                    DataColumnSpec cspec = (DataColumnSpec) o;
                    m_shell.replaceSelection(cspec.getName());
                    m_shell.requestFocus();
                }
            }
        });
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane listScroll = new JScrollPane(m_list);
        listScroll.setPreferredSize(new Dimension(150, 250));
        panel.add(listScroll, BorderLayout.WEST);
        JScrollPane shellScroll = new JScrollPane(m_shell);
        shellScroll.setPreferredSize(new Dimension(250, 250));
        panel.add(shellScroll, BorderLayout.CENTER);
        JScrollPane outputScroll = new JScrollPane(m_output);
        outputScroll.setPreferredSize(new Dimension(400, 150));
        panel.add(outputScroll, BorderLayout.SOUTH);
        super.setComponent(panel);
    }

    private void print(final REXP rexp, final String cmd) {
        Image image = null;
        try {
            image = getImage();
        } catch (Exception e) {
            LOGGER.error("Could not create image: ", e);
        }
        if (image != null) {
            Frame frame = null;
            Component comp = super.getComponent();
            while (comp != null) {
                if (comp instanceof Frame) {
                    frame = (Frame) comp;
                    break;
                }
                comp = comp.getParent();
            }
            JDialog f = new JDialog(frame, super.getViewTitle() + " - "  + cmd);
            Container cont = f.getContentPane();
            cont.add(new RPlotterViewPanel(image));
            f.pack();
            f.setVisible(true);
        }
        m_output.append(rexp.toString());
    }

    /**
     * @see de.unikn.knime.core.node.NodeView#modelChanged()
     */
    @Override
    protected void modelChanged() {
        m_shell.setText("");
        m_listModel.removeAllElements();
        RScriptingNodeModel model = (RScriptingNodeModel) super.getNodeModel();
        DataTableSpec spec = model.getDataTableSpec();
        for (int i = 0; i < spec.getNumColumns(); i++) {
            DataColumnSpec cspec = spec.getColumnSpec(i);
            DataColumnSpecCreator create = new DataColumnSpecCreator(
                    RConnection.formatColumn(cspec.getName()), cspec.getType());
            m_listModel.addElement(create.createSpec());
        }
    }

    /**
     * @see de.unikn.knime.core.node.NodeView#onClose()
     */
    @Override
    protected void onClose() {

    }

    /**
     * @see de.unikn.knime.core.node.NodeView#onOpen()
     */
    @Override
    protected void onOpen() {

    }

    private void createPNG() throws RSrvException {
        // we are careful here - not all R binaries support png
        // so we rather capture any failures
        REXP xp = RNodeModel.getRconnection().eval(
                "try(png(\"" + FILE_NAME + "\"))");
        if (xp.asString() != null) { // if there's a string then we have a
            // problem, R sent an error
            LOGGER.warn("Can't open png graphics device:\n"
                    + xp.asString());
            // this is analogous to 'warnings', but for us it's sufficient to
            // get just the 1st warning
            REXP w = RNodeModel.getRconnection().eval(
                    "if (exists(\"last.warning\") && "
                            + "length(last.warning)>0) names(last.warning)[1] "
                            + "else 0");
            if (w.asString() != null) {
                LOGGER.warn(w.asString());
            }
        }
    }

    private final Image getImage() throws IOException, RSrvException {
        RNodeModel.getRconnection().voidEval("dev.off()");
        // the file should be ready now, so let's read (ok this isn't pretty,
        // but hey, this ain't no beauty contest *grin* =)
        // we read in chunks of bufSize (64k by default) and store the resulting
        // byte arrays in a vector
        // ... just in case the file gets really big ...
        // we don't know the size in advance, because it's just a stream.
        // also we can't rewind it, so we have to store it piece-by-piece
        RFileInputStream is = RNodeModel.getRconnection().openFile(FILE_NAME);
        Vector<byte[]> buffers = new Vector<byte[]>();
        int bufSize = 65536;
        byte[] buf = new byte[bufSize];
        int imgLength = 0;
        int n = 0;
        while (true) {
            n = is.read(buf);
            if (n == bufSize) {
                buffers.addElement(buf);
                buf = new byte[bufSize];
            }
            if (n > 0) {
                imgLength += n;
            }
            if (n < bufSize) {
                break;
            }
        }

        if (imgLength < MIN_FILE_SIZE) {
            return null;
        }
        LOGGER.info("The image " + FILE_NAME + " has " + imgLength + " bytes.");

        // now let's join all the chunks into one, big array ...
        byte[] imgCode = new byte[imgLength];
        int imgPos = 0;
        for (Enumeration e = buffers.elements(); e.hasMoreElements();) {
            byte[] b = (byte[])e.nextElement();
            System.arraycopy(b, 0, imgCode, imgPos, bufSize);
            imgPos += bufSize;
        }
        if (n > 0) {
            System.arraycopy(buf, 0, imgCode, imgPos, n);
        }

        // ... and close the file ... and remove it - we have what we need :)
        is.close();
        // TODO m_rConn.removeFile(fileName);
        // TODO m_rConn.close();

        // now this is pretty boring AWT stuff, nothing to do with R ...
        return Toolkit.getDefaultToolkit().createImage(imgCode);
    }
}
