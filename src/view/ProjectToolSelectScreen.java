package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DataIO;
import model.Main;
import model.Project;
import model.UserSettings;

public class ProjectToolSelectScreen extends BaseSelectorScreen {

    public ProjectToolSelectScreen(int width, int height, Project project) {
        super(width, height, "Import Tool", "Tool", UserSettings.TOOL, "tool");
        remove(deleteButton);
        remove(importButton);
        remove(createButton);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toolName = listPane.getSelectedValue();
                try {
                    project.addTool(DataIO.loadTool(Main.userSettings.getFilePathFromName(toolName, UserSettings.TOOL)));
                }
                catch (Exception error) {
                    Main.userSettings.removeFromRecent(toolName, UserSettings.TOOL);
                    error.printStackTrace();
                }
                Main.BASE_FRAME.openProjectTools(false);
            }
        });
    }
}
