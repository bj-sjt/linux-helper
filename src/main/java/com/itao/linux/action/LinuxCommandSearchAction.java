package com.itao.linux.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.itao.linux.window.dialog.SearchDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LinuxCommandSearchAction extends AnAction {

    private SearchDialog dialog;
    public LinuxCommandSearchAction() {
        getTemplatePresentation().setIcon(AllIcons.Actions.Search);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {

        dialog = new SearchDialog("搜索命令", e.getProject());
        dialog.show();
    }

    public SearchDialog getDialog() {
        return dialog;
    }
}
