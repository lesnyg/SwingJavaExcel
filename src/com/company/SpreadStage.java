package com.company;

import javax.swing.*;
import java.awt.*;

public class SpreadStage extends JFrame {

    Container contentPane;
    public JTable table;

    public MenuBar mb;

    public Menu mFile;
    public Menu mHelp;

    public MenuItem mNew;
    public MenuItem mOpen;
    public MenuItem mExit;

    String[] colNames = {"A","B","C"};
    public Object data[][];

    public SpreadStage(String title) {
        super(title);
        this.setSize(600, 450);
        this.setLocation(300,100);
        initMenuBar();
        initBody();
        this.setVisible(true);
    }

    void initMenuBar(){
        mb = new MenuBar();

        mFile = new Menu("File");
        mHelp = new Menu("Help");

        mNew = new MenuItem("New");
        mOpen = new MenuItem("Open");
        mExit = new MenuItem("Exit");

        mFile.add(mNew);
        mFile.add(mOpen);
        mFile.addSeparator(); // 메뉴구분선
        mFile.add(mExit);

        mb.add(mFile);
        mb.setHelpMenu(mHelp);
        this.setMenuBar(mb);

        new MenuBarListener(this);

    }

    void initBody(){
        contentPane = this.getContentPane();

        data = new Object[50][6];
// row번호설정
//        for(int i = 0; i < 50; i++){
//            data[i][0] = i + 1;
//        }
        table = new JTable(data,colNames);
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane,BorderLayout.CENTER);
    }
}
