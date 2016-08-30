package com.liferay.ide.project.ui.upgrade.animated;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class DeployPage extends Page
{
    PageAction[] actions = { new PageFinishAction(), new PageSkipAction() };
    private String pageId = "deploy";
    
    public DeployPage( Composite parent, int style, LiferayUpgradeDataModel dataModel )
    {
        super( parent, style,dataModel );
        
        this.setLayout( new FillLayout() );
        
        Button button = new Button(this, SWT.PUSH);
        button.setText( "Deploy" );

        setActions( actions );
        this.setPageId( pageId );
    }

}
