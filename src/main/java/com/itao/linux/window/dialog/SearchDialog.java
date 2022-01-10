package com.itao.linux.window.dialog;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.itao.linux.bean.Command;
import com.itao.linux.util.HttpUtil;
import com.itao.linux.window.LinuxHelperForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static com.itao.linux.util.Constants.LINUX_COOL_URL;
import static com.itao.linux.util.Constants.LINUX_HELPER_TOOL_WINDOW;

public class SearchDialog extends DialogWrapper {
    EditorTextField textField;
    Project project;
    public SearchDialog(String title, Project project) {
        super(true);
        init();
        setTitle(title);
        this.project = project;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel jPanel = new JPanel();
        textField = new EditorTextField();
        textField.setPlaceholder("输入linux命令");
        textField.setPreferredSize(new Dimension(100,30));
        jPanel.add(textField);
        return jPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel jPanel = new JPanel();
        JButton searchBtn = new JButton("搜索");
        searchBtn.addActionListener(e -> {
            createToolWindowContent();
        });
        jPanel.add(searchBtn);

        return jPanel;
    }

    public void createToolWindowContent(){
        if (textField != null && !"".equals(textField.getText())) {
            HttpResponse httpResponse = HttpRequest.get(LINUX_COOL_URL + textField.getText()).execute();
            Command command;
            if (httpResponse.getStatus() == 200) {
                command = HttpUtil.getCommand(httpResponse.body());
            } else {
                command = HttpUtil.getCommand(null);
            }
            this.close(0);
            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(LINUX_HELPER_TOOL_WINDOW);
            Objects.requireNonNull(toolWindow);
            toolWindow.show(() -> {
                toolWindow.getContentManager().removeAllContents(true);
                LinuxHelperForm linuxHelperForm = new LinuxHelperForm();
                Content content = ContentFactory.SERVICE.getInstance()
                        .createContent(linuxHelperForm.getContent(command), "", true);
                toolWindow.getContentManager().addContent(content);
            });
        }
    }
}
