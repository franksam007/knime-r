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

import de.unikn.knime.core.node.NodeDialogPane;
import de.unikn.knime.core.node.NodeFactory;
import de.unikn.knime.core.node.NodeModel;
import de.unikn.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for R Console.
 * 
 * @author Thomas Gabriel, Konstanz University
 */
public class RConsoleFactory extends NodeFactory {
    /**
     * @see de.unikn.knime.core.node.NodeFactory#createNodeModel()
     */
    @Override
    public NodeModel createNodeModel() {
        return new RConsoleModel();
    }

    /**
     * @see de.unikn.knime.core.node.NodeFactory#getNrNodeViews()
     */
    @Override
    public int getNrNodeViews() {
        return 0;
    }

    /**
     * @see de.unikn.knime.core.node.NodeFactory#createNodeView(int,
     *      de.unikn.knime.core.node.NodeModel)
     */
    @Override
    public NodeView createNodeView(final int viewIndex,
            final NodeModel nodeModel) {
        return null;
    }

    /**
     * @see de.unikn.knime.core.node.NodeFactory#hasDialog()
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * @see de.unikn.knime.core.node.NodeFactory#createNodeDialogPane()
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new RConsoleDialogPane();
    }
}
