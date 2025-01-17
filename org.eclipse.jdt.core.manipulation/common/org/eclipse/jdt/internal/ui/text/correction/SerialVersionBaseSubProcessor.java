/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
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
package org.eclipse.jdt.internal.ui.text.correction;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;

import org.eclipse.jdt.internal.corext.fix.IProposableFix;
import org.eclipse.jdt.internal.corext.fix.PotentialProgrammingProblemsFixCore;

/**
 * Subprocessor for serial version quickfix proposals.
 *
 * @since 3.1
 */
public abstract class SerialVersionBaseSubProcessor<T> {
	public SerialVersionBaseSubProcessor() {
	}

	/**
	 * Determines the serial version quickfix proposals.
	 *
	 * @param context the invocation context
	 * @param location the problem location
	 * @param proposals the proposal collection to extend
	 */
	public void addSerialVersionProposals(final IInvocationContextCore context, final IProblemLocationCore location, final Collection<T> proposals) {

		Assert.isNotNull(context);
		Assert.isNotNull(location);
		Assert.isNotNull(proposals);

		IProposableFix[] fixes= PotentialProgrammingProblemsFixCore.createMissingSerialVersionFixes(context.getASTRoot(), location);
		if (fixes != null) {
			proposals.add(createSerialVersionProposal(fixes[0], IProposalRelevance.MISSING_SERIAL_VERSION_DEFAULT, context, true));
			proposals.add(createSerialVersionProposal(fixes[1], IProposalRelevance.MISSING_SERIAL_VERSION, context, false));
		}
	}

	protected abstract T createSerialVersionProposal(IProposableFix iProposableFix, int missingSerialVersion, IInvocationContextCore context, boolean b);

}
