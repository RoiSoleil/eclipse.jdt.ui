/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
 *     Renaud Waldura &lt;renaud+eclipse@waldura.com&gt; - New class/interface with wizard
 *     Rabea Gransberger <rgransberger@gmx.de> - [quick fix] Fix several visibility issues - https://bugs.eclipse.org/394692
 *     Jens Reimann <jreimann@redhat.com> Bug 38201: [quick assist] Allow creating abstract method - https://bugs.eclipse.org/38201
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.text.correction;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.correction.ICommandAccess;

import org.eclipse.jdt.internal.ui.text.correction.proposals.InitializeFinalFieldProposal;
import org.eclipse.jdt.internal.ui.text.correction.proposals.InitializeFinalFieldProposalCore;

public class UnInitializedFinalFieldSubProcessor extends UnInitializedFinalFieldBaseSubProcessor<ICommandAccess>{

	public static void getProposals(IInvocationContext context, IProblemLocationCore problem, Collection<ICommandAccess> proposals) throws CoreException {
		new UnInitializedFinalFieldSubProcessor().addProposals(context, problem, proposals);
	}

	public UnInitializedFinalFieldSubProcessor() {
	}

	@Override
	protected ICommandAccess createInitializeFinalFieldProposal(IProblemLocationCore problem, ICompilationUnit targetCU, SimpleName node, IVariableBinding targetBinding, int createConstructor) {
		return new InitializeFinalFieldProposal(problem, targetCU, node, targetBinding, IProposalRelevance.CREATE_CONSTRUCTOR);
	}

	@Override
	protected ICommandAccess createInitializeFinalFieldProposal(IProblemLocationCore problem, ICompilationUnit targetCU, MethodDeclaration node, int createConstructor, int updateAtConstructor) {
		return new InitializeFinalFieldProposal(problem, targetCU, node, IProposalRelevance.CREATE_CONSTRUCTOR, InitializeFinalFieldProposalCore.UPDATE_AT_CONSTRUCTOR);
	}

	@Override
	protected ICommandAccess conditionallyCreateInitializeFinalFieldProposal(IProblemLocationCore problem, ICompilationUnit targetCU, MethodDeclaration node, int createConstructor, int updateAtConstructor) {
		InitializeFinalFieldProposal initializeFinalFieldProposal= new InitializeFinalFieldProposal(problem, targetCU, node, IProposalRelevance.CREATE_CONSTRUCTOR, InitializeFinalFieldProposalCore.UPDATE_CONSTRUCTOR_NEW_PARAMETER);
		try {
			if(initializeFinalFieldProposal.hasProposal()) {
				return initializeFinalFieldProposal;
			}
		} catch(CoreException ce) {
		}
		return null;
	}

}
