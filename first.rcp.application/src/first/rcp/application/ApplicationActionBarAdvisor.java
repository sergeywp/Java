package first.rcp.application;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private IWorkbenchAction quitAction;
	private IWorkbenchAction aboutAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	quitAction = ActionFactory.QUIT.create(window);
    	register(quitAction);
    	
    	aboutAction = ActionFactory.ABOUT.create(window);
    	register(aboutAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	//добавляем файловое меню
    	menuBar.add(createFileMenu());
    	//добавляем группировку
    	menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    	//добавляем хелп меню
    	menuBar.add(createHelpMenu());
    }
    
    private IContributionItem createFileMenu(){
    	MenuManager menu = new MenuManager("&FILE",IWorkbenchActionConstants.M_FILE);
    	menu.add(quitAction);
    	return menu;
    }
    
    private IContributionItem createHelpMenu(){
    	MenuManager menu = new MenuManager("&HELP",IWorkbenchActionConstants.M_HELP);
    	menu.add(aboutAction);
    	return menu;
    }
    public void Dispose(){
    	quitAction = null;
    	aboutAction = null;
    	super.dispose();
    }
}
