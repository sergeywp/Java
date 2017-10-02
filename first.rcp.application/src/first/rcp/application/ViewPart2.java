package first.rcp.application;

import java.awt.GridLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

public class ViewPart2 extends ViewPart {
	public static final String ID = ViewPart2.class.getCanonicalName();

	public ViewPart2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite top = new Composite(parent, SWT.NONE);
		top.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL,
				GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		GridLayout layout = new GridLayout();
		//тут неработающий код проверить
		
		//top.setLayout(layout);
		new Button(top,SWT.PUSH).setText("fuck");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
