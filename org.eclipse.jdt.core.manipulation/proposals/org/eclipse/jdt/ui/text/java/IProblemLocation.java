/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.ui.text.java;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import org.eclipse.jdt.internal.ui.text.correction.IProblemLocationCore;

/**
 * Problem information for quick fix and quick assist processors.
 * <p>
 * Note: this interface is not intended to be implemented.
 * </p>
 *
 * Previously introduced in org.eclipse.jdt.ui version 3.0
 * @since 1.21
 *
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IProblemLocation extends IProblemLocationCore {

	/**
	 * Returns the start offset of the problem.
	 *
	 * @return the start offset of the problem
	 */
	@Override
	int getOffset();

	/**
	 * Returns the length of the problem.
	 *
	 * @return the length of the problem
	 */
	@Override
	int getLength();

	/**
	 * Returns the marker type of this problem.
	 *
	 * @return The marker type of the problem.
	 * @since 3.2
	 */
	@Override
	String getMarkerType();

	/**
	 * Returns the id of problem. Note that problem ids are defined per problem marker type.
	 * See {@link org.eclipse.jdt.core.compiler.IProblem} for id definitions for problems of type
	 * <code>org.eclipse.jdt.core.problem</code> and <code>org.eclipse.jdt.core.task</code>.
	 *
	 * @return The id of the problem.
	 */
	@Override
	int getProblemId();

	/**
	 * Returns the original arguments recorded into the problem.
	 *
	 * @return String[] Returns the problem arguments.
	 */
	@Override
	String[] getProblemArguments();

	/**
	 * Returns if the problem has error severity.
	 *
	 * @return <code>true</code> if the problem has error severity
	 */
	@Override
	boolean isError();

	/**
	 * Convenience method to evaluate the AST node covering this problem.
	 *
	 * @param astRoot The root node of the current AST
	 * @return Returns the node that covers the location of the problem
	 */
	@Override
	ASTNode getCoveringNode(CompilationUnit astRoot);

	/**
	 * Convenience method to evaluate the AST node covered by this problem.
	 *
	 * @param astRoot The root node of the current AST
	 * @return Returns the node that is covered by the location of the problem
	 */
	@Override
	ASTNode getCoveredNode(CompilationUnit astRoot);

}
