package com.itao.linux.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class LinuxHelperFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        LinuxHelperForm linuxHelperForm = new LinuxHelperForm();
        Content content = ContentFactory.SERVICE.getInstance()
                .createContent(linuxHelperForm.getContent(), "", true);
        //toolWindow.setIcon(IconLoader.getIcon("/icon/linux.svg", LinuxHelperFactory.class));
        toolWindow.getContentManager().addContent(content);
    }
}
