/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import uk.ac.bolton.archimate.editor.model.IModelExporter;
import uk.ac.bolton.archimate.editor.model.IModelImporter;
import uk.ac.bolton.archimate.editor.ui.IArchimateImages;
import uk.ac.bolton.archimate.editor.ui.ImageFactory;
import uk.ac.bolton.archimate.editor.ui.ViewManager;
import uk.ac.bolton.archimate.editor.utils.PlatformUtils;
import uk.ac.bolton.archimate.editor.views.navigator.INavigatorView;
import uk.ac.bolton.archimate.editor.views.tree.ITreeModelView;


/**
 * An action bar advisor is responsible for creating, adding, and disposing of the
 * actions added to a workbench window. Each window will be populated with
 * new actions.
 * 
 * @author Phillip Beauvoir
 */
public class ArchimateEditorActionBarAdvisor
extends ActionBarAdvisor {
    
    private IWorkbenchAction fActionNewArchimateModel;
    private IWorkbenchAction fActionNewArchimateModelFromTemplate;
    private IWorkbenchAction fActionOpenModel;
    private IWorkbenchAction fActionOpenDiagram;
    private IWorkbenchAction fActionCloseModel;
    private IWorkbenchAction fActionCloseEditor;
    private IWorkbenchAction fActionCloseAllEditors;
    private IWorkbenchAction fActionSave;
    private IWorkbenchAction fActionSaveAs;
    private IWorkbenchAction fActionSaveAsTemplate;
    private IWorkbenchAction fActionQuit;
    private IWorkbenchAction fActionAbout;
    private IWorkbenchAction fActionProperties;
    private IWorkbenchAction fActionPrint;
    
    private IWorkbenchAction fActionImportBiZZ;
    
    private IWorkbenchAction fActionExportBiZZ;
    private IWorkbenchAction fActionExportCSV;
    private IWorkbenchAction fActionExportHTML;
    
    private IWorkbenchAction fActionCut;
    private IWorkbenchAction fActionCopy;
    private IWorkbenchAction fActionPaste;
    private IWorkbenchAction fActionDelete;
    private IWorkbenchAction fActionRename;
    
    private IWorkbenchAction fActionUndo;
    private IWorkbenchAction fActionRedo;

    private IWorkbenchAction fActionSelectAll;

    private IWorkbenchAction fActionResetPerspective;
    private Action fActionToggleCoolbar;
    
    private IAction fShowModelsView;
    private IAction fShowPropertiesView;
    private IAction fShowOutlineView;
    private IAction fShowNavigatorView;

    
    /**
     * Constructor
     * @param configurer
     */
    public ArchimateEditorActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    @Override
    protected void makeActions(final IWorkbenchWindow window) {
        
        // Open Model
        fActionOpenModel = new OpenModelAction(window);
        register(fActionOpenModel);
        
        // New Archimate model
        fActionNewArchimateModel = new NewArchimateModelAction();
        register(fActionNewArchimateModel);
        
        // New Archimate model from Template
        fActionNewArchimateModelFromTemplate = new NewArchimateModelFromTemplateAction(window);
        register(fActionNewArchimateModelFromTemplate);
        
        // Open Diagram
        fActionOpenDiagram = ArchimateEditorActionFactory.OPEN_DIAGRAM.create(window);
        register(fActionOpenDiagram);
        
        // Close Model
        fActionCloseModel = ArchimateEditorActionFactory.CLOSE_MODEL.create(window);
        register(fActionCloseModel);
        
        // Close Editor
        fActionCloseEditor = ActionFactory.CLOSE.create(window);
        fActionCloseEditor.setText("Close View");
        register(fActionCloseEditor);

        // Close All Editors
        fActionCloseAllEditors = ActionFactory.CLOSE_ALL.create(window);
        fActionCloseAllEditors.setText("Close All Views");
        register(fActionCloseAllEditors);
        
        // Save
        fActionSave = ArchimateEditorActionFactory.SAVE_MODEL.create(window);
        register(fActionSave);
        
        // Save As
        fActionSaveAs = ArchimateEditorActionFactory.SAVE_AS.create(window);
        register(fActionSaveAs);
        
        // Save As Template
        fActionSaveAsTemplate = ArchimateEditorActionFactory.SAVE_AS_TEMPLATE.create(window);
        register(fActionSaveAsTemplate);
        
        // Import from BiZZdesign Architect
        fActionImportBiZZ = new ImportFromBiZZAction(window);
        register(fActionImportBiZZ);
        
        // Export to BiZZdesign Architect
        fActionExportBiZZ = new ExportToBiZZAction(window);
        register(fActionExportBiZZ);
        
        // Export to CSV
        fActionExportCSV = new ExportToCSVAction(window);
        register(fActionExportCSV);
        
        // Export to HTML Report
        fActionExportHTML = new ExportAsHTMLReportAction(window);
        register(fActionExportHTML);
        
        // Properties
        fActionProperties = ActionFactory.PROPERTIES.create(window);
        register(fActionProperties);
        
        // Quit
        fActionQuit = ActionFactory.QUIT.create(window);
        register(fActionQuit);
        
        // Undo
        fActionUndo = ActionFactory.UNDO.create(window);
        register(fActionUndo);

        // Redo
        fActionRedo = ActionFactory.REDO.create(window);
        register(fActionRedo);
        
        // Cut
        fActionCut = ActionFactory.CUT.create(window);
        register(fActionCut);
        
        // Copy
        fActionCopy = ActionFactory.COPY.create(window);
        register(fActionCopy);
        
        // Paste
        fActionPaste = ActionFactory.PASTE.create(window);
        register(fActionPaste);
        
        // Delete
        fActionDelete = ArchimateEditorActionFactory.DELETE.create(window);
        register(fActionDelete);
        
        // Rename
        fActionRename = ArchimateEditorActionFactory.RENAME.create(window);
        register(fActionRename);
        
        // Select All
        fActionSelectAll = ActionFactory.SELECT_ALL.create(window);
        register(fActionSelectAll);
        
        // Print
        fActionPrint = ActionFactory.PRINT.create(window);
        register(fActionPrint);
        
        // About
        fActionAbout = ActionFactory.ABOUT.create(window);
        register(fActionAbout);
        
        // Reset Perspective
        fActionResetPerspective = ActionFactory.RESET_PERSPECTIVE.create(window);
        fActionResetPerspective.setText("Reset Window Layout");
        register(fActionResetPerspective);
        
        // Toggle Coolbar
        fActionToggleCoolbar = new ShowToolbarAction();
        
        // Show Views
        fShowModelsView = new ToggleViewAction(ITreeModelView.NAME, ITreeModelView.ID,
                "uk.ac.bolton.archimate.editor.action.showTreeModelView", ITreeModelView.IMAGE_DESCRIPTOR);
        register(fShowModelsView);
        
        fShowPropertiesView = new ToggleViewAction("Properties", ViewManager.PROPERTIES_VIEW,
                "uk.ac.bolton.archimate.editor.action.showPropertiesView", IArchimateImages.ImageFactory.getImageDescriptor(ImageFactory.ECLIPSE_IMAGE_PROPERTIES_VIEW_ICON));
        register(fShowPropertiesView);
        
        fShowOutlineView = new ToggleViewAction("Outline", ViewManager.OUTLINE_VIEW,
                "uk.ac.bolton.archimate.editor.action.showOutlineView", IArchimateImages.ImageFactory.getImageDescriptor(ImageFactory.ECLIPSE_IMAGE_OUTLINE_VIEW_ICON));
        register(fShowOutlineView);
        
        fShowNavigatorView = new ToggleViewAction("Navigator", INavigatorView.ID,
                "uk.ac.bolton.archimate.editor.action.showNavigatorView", INavigatorView.IMAGE_DESCRIPTOR);
        register(fShowNavigatorView);
    }
    
    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
        // File
        menuBar.add(createFileMenu());

        // Edit
        menuBar.add(createEditMenu());
        
        // Window
        menuBar.add(createWindowMenu());

        // Help
        menuBar.add(createHelpMenu());
    }
    
    /**
     * Create the File menu
     * @return
     */
    private MenuManager createFileMenu() {
        IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();
        
        MenuManager menu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));

        // New
        MenuManager newMenu = new MenuManager("&New", "new_menu");
        menu.add(newMenu);
        newMenu.add(fActionNewArchimateModel);
        newMenu.add(fActionNewArchimateModelFromTemplate);
        menu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
        
        menu.add(fActionOpenModel);
        
        // Open Recent
        MenuManager openRecentMenu = new MRUMenuManager(window);
        menu.add(openRecentMenu);
        
        menu.add(new Separator());
        
        menu.add(fActionOpenDiagram);
        menu.add(fActionCloseModel);
        menu.add(fActionCloseEditor);
        menu.add(fActionCloseAllEditors);
        menu.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
        menu.add(new Separator());

        menu.add(fActionSave);
        menu.add(fActionSaveAs);
        menu.add(fActionSaveAsTemplate);
        menu.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
        menu.add(new Separator());
        
        menu.add(fActionPrint);
        menu.add(new Separator());
        
        MenuManager importMenu = new MenuManager("&Import", "import_menu");
        menu.add(importMenu);
        importMenu.add(fActionImportBiZZ);
        addImportModelExtensionMenuItems(window, importMenu);
        importMenu.add(new GroupMarker("import_ext"));
        importMenu.add(new Separator());
        
        MenuManager exportMenu = new MenuManager("&Export", "export_menu");
        menu.add(exportMenu);
        exportMenu.add(fActionExportBiZZ);
        exportMenu.add(fActionExportCSV);
        addExportModelExtensionMenuItems(window, exportMenu);
        exportMenu.add(new GroupMarker("export_ext"));
        exportMenu.add(new Separator());
        
        MenuManager reportMenu = new MenuManager("&Report", "report_menu");
        menu.add(reportMenu);
        reportMenu.add(fActionExportHTML);
        
        menu.add(new Separator());
        
        menu.add(fActionProperties);
        menu.add(new Separator());
        
        // Not needed on a Mac
        if(!PlatformUtils.isMac()) {
            menu.add(fActionQuit);
        }
        
        menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));

        return menu;
    }

    /**
     * Create the Editor menu
     * @return
     */
    private MenuManager createEditMenu() {
        IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();
        
        MenuManager menu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
        menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));
        
        menu.add(fActionUndo);
        menu.add(fActionRedo);
        menu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
        menu.add(new Separator());
        
        menu.add(fActionCut);
        menu.add(fActionCopy);
        menu.add(fActionPaste);
        menu.add(fActionDelete);
        menu.add(new Separator(IWorkbenchActionConstants.CUT_EXT));
        
        menu.add(fActionRename);
        menu.add(new Separator(fActionRename.getId()));
        
        menu.add(fActionSelectAll);
        menu.add(new Separator(fActionSelectAll.getId()));
        
        menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        
        /*
         * On a Mac, Eclipse adds a "Preferences" menu item under the application menu bar.
         * However, it does nothing unless you add the Preferences menu item manually elsewhere.
         * See - http://dev.eclipse.org/newslists/news.eclipse.platform.rcp/msg30749.html
         * 
         */
        IWorkbenchAction preferenceAction = ActionFactory.PREFERENCES.create(window);
        ActionContributionItem item = new ActionContributionItem(preferenceAction);
        item.setVisible(!PlatformUtils.isMac());
        
        menu.add(new Separator());
        menu.add(item);

        menu.add(new Separator(IWorkbenchActionConstants.EDIT_END));
        return menu;
    }

    /**
     * Create the Window menu
     * @return
     */
    private MenuManager createWindowMenu() {
        IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();

        MenuManager menu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);

        //MenuManager perspectiveMenu = new MenuManager(Messages.LDAuthorActionBarAdvisor_10, "openPerspective"); //$NON-NLS-1$
        //IContributionItem perspectiveList = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);
        //perspectiveMenu.add(perspectiveList);
        //menu.add(perspectiveMenu);
        
        //MenuManager showViewMenu = new MenuManager(Messages.LDAuthorActionBarAdvisor_11);
        //menu.add(showViewMenu);

        //IContributionItem viewList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
        //showViewMenu.add(viewList);

        //menu.add(new Separator("PerspectiveMenu")); //$NON-NLS-1$
        
        menu.add(fShowModelsView);
        menu.add(fShowPropertiesView);
        menu.add(fShowOutlineView);
        menu.add(fShowNavigatorView);
        menu.add(new GroupMarker("show_view"));
        menu.add(new Separator());

        menu.add(fActionResetPerspective);
        menu.add(fActionToggleCoolbar);

        menu.add(new Separator("nav")); //$NON-NLS-1$

        MenuManager navigationMenu = new MenuManager("Navigation");
        menu.add(navigationMenu);

        IAction a = ActionFactory.NEXT_EDITOR.create(window);
        register(a);
        navigationMenu.add(a);

        a = ActionFactory.PREVIOUS_EDITOR.create(window);
        register(a);
        navigationMenu.add(a);

        navigationMenu.add(new Separator());

        a = ActionFactory.NEXT_PART.create(window);
        register(a);
        navigationMenu.add(a);

        a = ActionFactory.PREVIOUS_PART.create(window);
        register(a);
        navigationMenu.add(a);

        menu.add(ContributionItemFactory.OPEN_WINDOWS.create(window));

        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        
        return menu;
    }

    /**
     * Create the Help menu
     * @return
     */
    private MenuManager createHelpMenu() {
        IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();
        
        MenuManager menu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        
        menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
        
        menu.add(ActionFactory.HELP_CONTENTS.create(window));
        menu.add(ActionFactory.HELP_SEARCH.create(window));
        menu.add(ActionFactory.DYNAMIC_HELP.create(window));
        
        menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
        
        /*
         * On a Mac, Eclipse adds an "About" menu item under the application menu bar.
         * However, it does nothing unless you add the About menu item manually elsewhere.
         * See - http://dev.eclipse.org/newslists/news.eclipse.platform.rcp/msg30749.html
         * 
         */
        ActionContributionItem item = new ActionContributionItem(fActionAbout);
        item.setVisible(!PlatformUtils.isMac());
        
        menu.add(new Separator());
        menu.add(item);

        return menu;
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBarManager) {
        IToolBarManager toolBarFile = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBarManager.add(new ToolBarContributionItem(toolBarFile, "toolbar_file")); //$NON-NLS-1$
        
        toolBarFile.add(new GroupMarker("start")); //$NON-NLS-1$
        // New
        NewDropDownAction newDropDown = new NewDropDownAction();
        newDropDown.add(fActionNewArchimateModel);
        newDropDown.add(fActionNewArchimateModelFromTemplate);
        toolBarFile.add(newDropDown);
        toolBarFile.add(fActionOpenModel);
        toolBarFile.add(fActionSave);
        toolBarFile.add(new GroupMarker("end")); //$NON-NLS-1$

        IToolBarManager toolBarEdit = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBarManager.add(new ToolBarContributionItem(toolBarEdit, "toolbar_edit")); //$NON-NLS-1$
        
        toolBarEdit.add(new GroupMarker("start")); //$NON-NLS-1$
        toolBarEdit.add(fActionUndo);
        toolBarEdit.add(fActionRedo);
        toolBarEdit.add(new Separator());
        toolBarEdit.add(fActionCut);
        toolBarEdit.add(fActionCopy);
        toolBarEdit.add(fActionPaste);
        toolBarEdit.add(fActionDelete);
        toolBarEdit.add(new GroupMarker("end")); //$NON-NLS-1$
        
        IToolBarManager toolBarViews = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBarManager.add(new ToolBarContributionItem(toolBarViews, "toolbar_views")); //$NON-NLS-1$
        
        toolBarViews.add(new GroupMarker("start")); //$NON-NLS-1$
        toolBarViews.add(fShowModelsView);
        toolBarViews.add(fShowPropertiesView);
        toolBarViews.add(fShowOutlineView);
        toolBarViews.add(fShowNavigatorView);
        toolBarViews.add(new GroupMarker("end")); //$NON-NLS-1$
        toolBarViews.add(new Separator());
    }

    /**
     * Add any contributed export model menu items
     */
    private void addExportModelExtensionMenuItems(IWorkbenchWindow window, IMenuManager exportMenu) {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint("uk.ac.bolton.archimate.editor.exportHandler");
        if(extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(IExtension extension : extensions) {
                IConfigurationElement[] elements = extension.getConfigurationElements();
                for(IConfigurationElement configurationElement : elements) {
                    try {
                        String id = configurationElement.getAttribute("id");
                        String label = configurationElement.getAttribute("label");
                        IModelExporter exporter = (IModelExporter)configurationElement.createExecutableExtension("class");
                        if(id != null && label != null && exporter != null) {
                            ExportModelAction action = new ExportModelAction(window, id, label, exporter);
                            exportMenu.add(action);
                        }
                    } 
                    catch(CoreException ex) {
                        ex.printStackTrace();
                    } 
                }
            }
        }
    }
    
    /**
     * Add any contributed import model menu items
     */
    private void addImportModelExtensionMenuItems(IWorkbenchWindow window, IMenuManager importMenu) {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint("uk.ac.bolton.archimate.editor.importHandler");
        if(extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for(IExtension extension : extensions) {
                IConfigurationElement[] elements = extension.getConfigurationElements();
                for(IConfigurationElement configurationElement : elements) {
                    try {
                        String id = configurationElement.getAttribute("id");
                        String label = configurationElement.getAttribute("label");
                        IModelImporter importer = (IModelImporter)configurationElement.createExecutableExtension("class");
                        if(id != null && label != null && importer != null) {
                            ImportModelAction action = new ImportModelAction(window, id, label, importer);
                            importMenu.add(action);
                        }
                    } 
                    catch(CoreException ex) {
                        ex.printStackTrace();
                    } 
                }
            }
        }
    }
}