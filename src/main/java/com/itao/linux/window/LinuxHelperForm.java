package com.itao.linux.window;

import com.intellij.util.ui.JBUI;
import com.itao.linux.bean.Command;
import com.itao.linux.bean.Param;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

import static com.itao.linux.bean.CommandData.TITLES;
import static com.itao.linux.util.Constants.COMMAND_NOT_EIXST;
import static com.itao.linux.util.Constants.SEARCH_COMMAND;

public class LinuxHelperForm {
    // 根容器
    private JPanel root;
    // 内容容器
    private JPanel contentPanel;
    // 标题容器
    private JPanel titlePanel;
    // 标题
    private JLabel title;
    // 格式容器
    private JPanel formatPanel;
    // 语法格式
    private JLabel format;
    // 参数列表容器
    private JPanel tablePanel;

    private JScrollPane tableScrollPanel;
    // 参数列表
    private JTable paramTable;


    private final Border margin = JBUI.Borders.emptyLeft(30);

    public LinuxHelperForm() {
    }

    public JComponent getContent(Command command) {
        if (!command.isExist()) {
            title.setText(COMMAND_NOT_EIXST);
            title.setBorder(margin);
            title.setFont(new Font(null, Font.BOLD, 28));
        } else {
            title.setText(command.getTitle());
            title.setBorder(margin);
            title.setFont(new Font(null, Font.BOLD, 28));
            format.setText(command.getGrammarFormat());
            format.setBorder(margin);
            format.setFont(new Font(null, Font.PLAIN, 18));
            Vector<Vector<String>> vector = new Vector<>();
            List<Param> params = command.getParams();
            params.forEach(param -> {
                Vector<String> datas = new Vector<>();
                datas.add(param.getName());
                datas.add(param.getDesc());
                vector.add(datas);
            });
            TableModel model = new DefaultTableModel(vector, new Vector<>(List.of(TITLES)));
            paramTable.setModel(model);
        }
        return root;
    }

    public JComponent getContent() {
        title.setText(SEARCH_COMMAND);
        title.setBorder(margin);
        title.setFont(new Font(null, Font.BOLD, 28));
        return root;
    }
}
