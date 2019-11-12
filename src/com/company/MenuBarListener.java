package com.company;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;



public class MenuBarListener implements ActionListener {

    private SpreadStage stage;
    private JFileChooser jfc;

    public MenuBarListener(SpreadStage stage) {
        this.stage = stage;
        this.stage.mNew.addActionListener(this);
        this.stage.mOpen.addActionListener(this);
        this.stage.mExit.addActionListener(this);
        jfc = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == stage.mOpen){
// 파일 필터
            jfc.setFileFilter(new FileNameExtensionFilter("excel[xls형식만지원]", "xls"));
// 멀티 셀렉트
// jfc.setMultiSelectionEnabled(false);
            if(jfc.showOpenDialog(stage) == JFileChooser.APPROVE_OPTION){
                File file = jfc.getSelectedFile();
                List<HashMap<String, String>> list = MenuBarService.fileLoader(file);
                for(int i = 0; i < list.size(); i++){
                    int sizeMap = list.get(i).size();
                    for(int j = 0; j < sizeMap ; j++){
                        stage.data[i][j+1] = list.get(i).get("attr"+j);
                    }
                }
                stage.repaint();
            }
        }else if(e.getSource() == stage.mNew){
            for(int i = 0; i < 50; i++){
                for(int k = 1; k < 6; k++){
                    stage.data[i][k] = "";
                }
            }
            stage.repaint();
        }
        else if(e.getSource() == stage.mExit){
            System.exit(0);
        }
    }
}
