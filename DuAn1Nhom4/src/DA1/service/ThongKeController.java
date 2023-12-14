/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import java.util.ArrayList;
import javax.swing.JFrame;
import DA1.model.ThongKe;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Welcome
 */
public class ThongKeController {

    private ThongKeService tks = new ThongKeService();
    private ArrayList<ThongKe> list1 = new ArrayList<>();
    private ThongKe tk = new ThongKe();

    public void setDataToChart1(JPanel jpnItem) {
        list1 = tks.getAll();
        if (list1 != null) {
            DefaultCategoryDataset dts = new DefaultCategoryDataset();
            for (ThongKe tk : list1) {
                dts.addValue(tk.getDt(), "Doanh Thu", tk.getNgay());
            }

            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO NGÀY", "Thời gian", "Doanh thu", dts);
            ChartPanel cp = new ChartPanel(chart);
            cp.setPreferredSize(new Dimension(jpnItem.getWidth(),300));
            
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(cp);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }
}
