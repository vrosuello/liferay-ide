/*******************************************************************************
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/
package com.liferay.ide.ui.editor;

import java.io.File;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.propertiesfileeditor.IPropertiesFilePartitions;
import org.eclipse.jdt.internal.ui.propertiesfileeditor.PropertiesFileSourceViewerConfiguration;
import org.eclipse.jface.internal.text.html.HTMLTextPresenter;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;


/**
 * @author Gregory Amerson
 */
@SuppressWarnings( "restriction" )
public class LiferayPropertiesSourceViewerConfiguration extends PropertiesFileSourceViewerConfiguration
{
    private File propertiesFile;

    public LiferayPropertiesSourceViewerConfiguration( ITextEditor editor )
    {
        super( JavaPlugin.getDefault().getJavaTextTools().getColorManager(),
            JavaPlugin.getDefault().getCombinedPreferenceStore(), editor,
            IPropertiesFilePartitions.PROPERTIES_FILE_PARTITIONING );
    }

    @Override
    public IContentAssistant getContentAssistant( final ISourceViewer sourceViewer )
    {
        final ContentAssistant assistant = new ContentAssistant()
        {
            @Override
            public IContentAssistProcessor getContentAssistProcessor( final String contentType )
            {
                return new LiferayPropertiesContentAssistProcessor( propertiesFile, contentType );
            }
        };

        assistant.setInformationControlCreator( getInformationControlCreator( sourceViewer ) );

        return assistant;
    }

    public IInformationControlCreator getInformationControlCreator( ISourceViewer sourceViewer )
    {
        return new IInformationControlCreator()
        {
            public IInformationControl createInformationControl( Shell parent )
            {
                return new DefaultInformationControl( parent, new HTMLTextPresenter( true ) );
            }
        };
    }

    void setPropertilesFile( File file )
    {
        this.propertiesFile = file;
    }
}
