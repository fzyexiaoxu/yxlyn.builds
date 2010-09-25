/*******************************************************************************
 * Copyright (c) 2010 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.builds.ui.editor;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.mylyn.builds.core.IArtifact;
import org.eclipse.mylyn.internal.builds.ui.editor.ArtifactsPart.ArtifactFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * @author Steffen Pingel
 */
public class ArtifactsLabelProvider extends LabelProvider implements IStyledLabelProvider {

	final Styler NO_STYLE = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
		}
	};

	public ArtifactsLabelProvider() {
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ArtifactFolder) {
			return WorkbenchImages.getImage(ISharedImages.IMG_OBJ_FOLDER);
		} else if (element instanceof IArtifact) {
			return WorkbenchImages.getImage(ISharedImages.IMG_OBJ_FILE);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IArtifact) {
			return ((IArtifact) element).getName();
		}
		if (element instanceof ArtifactFolder) {
			return ((ArtifactFolder) element).getName();
		}
		return null;
	}

	public StyledString getStyledText(Object element) {
		String text = getText(element);
		if (text != null) {
			StyledString styledString = new StyledString(text);
			return styledString;
		}
		return new StyledString();
	}

}