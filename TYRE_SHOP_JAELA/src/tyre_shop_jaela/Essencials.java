/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tyre_shop_jaela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.utils.FadingUtils;

/**
 *
 * @author Nalaranga
 */
public class Essencials {

    double xCor = 0;
    double yCor = 0;
    boolean kk = true;
    Dimension s;
    JList jlRsuggestion;
    JScrollPane Scrollpane;
    Border btfin;
    Border btfout;

    public Essencials() {
        btfin = new MatteBorder(0, 0, 1, 0, new Color(0, 153, 204));
        btfout = new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220));
        System.out.println("test");
    }

    public void setCenter(JFrame frame) {
        int sw = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        int sh = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;

        int fw = frame.getWidth() / 2;
        int fh = frame.getHeight() / 2;

        int x = sw - fw;
        int y = sh - fh;

        frame.setBounds(x, y, frame.getWidth(), frame.getHeight());
//        frame.setIconImage(new ImageIcon(getClass().getResource("/Icons/ICON_IMAGE.png")).getImage());
    }

    public void setDirection(JFrame frame) {
        xCor = MouseInfo.getPointerInfo().getLocation().getX() - frame.getX();
        yCor = MouseInfo.getPointerInfo().getLocation().getY() - frame.getY();
    }

    public void moveWindow(JFrame frame) {
        int u1 = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int u2 = (int) frame.getSize().getWidth();

        if (u1 == u2) {
            return;
        }

        double x1 = MouseInfo.getPointerInfo().getLocation().getX();
        double y1 = MouseInfo.getPointerInfo().getLocation().getY();

        int xx = (int) (x1 - xCor);
        int yy = (int) (y1 - yCor);

        frame.setLocation(xx, yy);
    }

    public void toolt(final JComponent tf, String message) {

        JLabel lb = new JLabel();
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lb.setText(message);
        lb.setForeground(Color.white);
//        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/emoticon-15-16 (2).png")));

        MinimalBalloonStyle minm = new MinimalBalloonStyle(Color.gray, 3);
        final BalloonTip baloon = new BalloonTip(tf, lb, minm, BalloonTip.Orientation.RIGHT_BELOW, BalloonTip.AttachLocation.ALIGNED, 20, 5, false);
        FadingUtils.fadeInBalloon(baloon, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            if (i == 19) {
                                FadingUtils.fadeOutBalloon(baloon, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        baloon.closeBalloon();
                                    }
                                }, 600, 24);
                            }
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                            }
                        }
                    }
                }).start();
            }
        }, 300, 24);

        baloon.setVisible(true);
    }

    public boolean maximizeWin(JFrame frame) {
        if (kk == true) {
            s = frame.getSize();
            Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            frame.setBounds(0, 0, maximumWindowBounds.width, maximumWindowBounds.height);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            kk = false;
            //  Maximized();

        } else {
            frame.setSize(s);
            setCenter(frame);
            kk = true;
            //     Minimized();
        }
        return kk;
    }

    public void changeHeader(String key, int index, JTable table) {
        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setHeaderValue(key);
        th.repaint();
    }

    public void JSuggestionField(java.awt.event.KeyEvent evt, Vector v, final JTextField tf, JPanel mainpanel, final JPopupMenu jPopupMenu, final JTextField secondTF, JButton bu) {
        try {

            jlRsuggestion = new JList();
            jlRsuggestion.setBackground(new java.awt.Color(255, 255, 255));
            jlRsuggestion.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
            jlRsuggestion.setForeground(new java.awt.Color(70, 70, 70));
            jlRsuggestion.setSelectionBackground(new Color(245, 245, 245));
            jlRsuggestion.setSelectionForeground(new Color(0, 153, 204));
            jlRsuggestion.setBorder(new MatteBorder(0, 10, 0, 10, Color.WHITE));

            Scrollpane = new JScrollPane();
            Scrollpane.setBorder(null);
            Scrollpane.setViewportView(jlRsuggestion);

            if (evt != null && "".equals(tf.getText())) {
                if (jPopupMenu.isVisible()) {
                    jPopupMenu.setVisible(false);
                }
                return;
            }

            if (evt == null || evt.getKeyCode() == 10) {
                if (jPopupMenu.isVisible()) {
                    jPopupMenu.setVisible(false);
                    return;
                }
            }

            jlRsuggestion.setFixedCellHeight(30);
            if (bu != null) {
                jlRsuggestion.setFixedCellWidth(tf.getWidth() + bu.getWidth() - 16);
            } else {
                jlRsuggestion.setFixedCellWidth(tf.getWidth() - 21);
            }
            jlRsuggestion.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    listSelecterKey(evt);
                }

                private void listSelecterKey(KeyEvent evt) {
                    // if (jlRsuggestion.getSelectedIndex() > 0) {
                    //   if ((jlRsuggestion.getSelectedValue().toString() != null) || (!jlRsuggestion.getSelectedValue().toString().equals(""))) {
                    String ar[] = jlRsuggestion.getSelectedValue().toString().split("-");
                    if (ar.length < 2) {
                        tf.setText(jlRsuggestion.getSelectedValue().toString());
                    } else {
                        tf.setText(ar[1]);
                        // secondTF.setText(ar[0]);
                    }
                    if (evt.getKeyCode() != 40 & evt.getKeyCode() != 38 & evt.getKeyCode() != 10) {
                        tf.grabFocus();
                        tf.selectAll();
                    }
                    if (evt.getKeyCode() == 10) {
                        if (jPopupMenu.isVisible()) {
                            jPopupMenu.setVisible(false);
                            if (tf != null) {
                                tf.grabFocus();
                            }
                        }
                    }
                    //  }
                    //}
                }
            });

            jlRsuggestion.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    listSelectMouse(evt);
                }

                private void listSelectMouse(MouseEvent evt) {
                    String ar[] = jlRsuggestion.getSelectedValue().toString().split("-");
                    if (ar.length < 2) {
                        tf.setText(jlRsuggestion.getSelectedValue().toString());
                    } else {
                        tf.setText(ar[1]);
                        //secondTF.setText(ar[0]);
                    }

                    if (evt.getClickCount() == 2) {
                        if (jPopupMenu.isVisible()) {
                            jPopupMenu.setVisible(false);
                            if (tf != null) {
                                tf.grabFocus();
                            }
                        }
                    }

//                    tf.setText(jlRsuggestion.getSelectedValue().toString());
//                    if (jPopupMenu.isVisible()) {
//                        jPopupMenu.setVisible(false);
//                        if (tf != null) {
//                            tf.grabFocus();
//                        }
//                    }
                }
            });
            if (v.isEmpty()) {
                if (jPopupMenu.isVisible()) {
                    jPopupMenu.setVisible(false);
                }
                return;
            }

            jPopupMenu.setVisible(false);

            jlRsuggestion.removeAll();
            jlRsuggestion.setListData(v);
            jPopupMenu.removeAll();
            jPopupMenu.add(Scrollpane);
            jPopupMenu.show(mainpanel, tf.getX(), tf.getY() + tf.getHeight() - 1);

            tf.grabFocus();

            if (evt != null && evt.getKeyCode() == 40) {
                jlRsuggestion.grabFocus();
                jlRsuggestion.setSelectedIndex(0);
                String ar[] = jlRsuggestion.getSelectedValue().toString().split("-");
                if (ar.length < 2) {
                    tf.setText(jlRsuggestion.getSelectedValue().toString());
                } else {
                    tf.setText(ar[1]);
                    // secondTF.setText(ar[0]);
                }
            }
            if (evt != null && evt.getKeyCode() == 10) {
                jPopupMenu.setVisible(false);
                if (secondTF != null) {
                    secondTF.grabFocus();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void animOut(final JFrame fg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DecimalFormat def = new DecimalFormat("0.00");
                float p = 1.0f;
                while (p >= 0.01f) {
                    fg.setOpacity(Float.parseFloat(def.format(p)));
                    p = p - 0.01f;
                    if (Float.parseFloat(def.format(p)) == 0.2f) {
                        fg.dispose();
                        Thread.yield();
                    }
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

        // fg.dispose();
    }

    public void FocusIn(JTextField tf, JLabel lb) {
        tf.setBorder(btfin);
        lb.setForeground(new Color(100, 100, 100));
        lb.setBorder(btfin);
    }

    public void FocusOut(JTextField tf, JLabel lb) {
        lb.setForeground(new Color(200, 200, 200));
        tf.setBorder(btfout);
        lb.setBorder(btfout);
    }

    public void TPFocusIn(JScrollPane js, JTextPane tf, String Default) {
        js.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 153, 204)));
        tf.setForeground(new Color(100, 100, 100));
        if (Default.equals(tf.getText())) {
            tf.setText("");
            tf.setForeground(new Color(100, 100, 100));
        }
    }

    public void TPFocusOut(JScrollPane js, JTextPane tf, String Default) {

        if ("".equals(tf.getText())) {
            tf.setText(Default);
            tf.setForeground(new Color(200, 200, 200));
            js.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        }
    }

    public void customTBHEAD(JTable table, String tbVals[], int rows, Color c) {
        JTableHeader head = table.getTableHeader();
        head.setBackground(new java.awt.Color(252, 252, 252));
        head.setPreferredSize(new Dimension(100, 30));
        TableColumnModel tableColumnModel = head.getColumnModel();
        TableCellRenderer renderer = new JComponentTableCellRenderer();
        Font tbHFont = new Font("Segoe UI", Font.PLAIN, 12);
        for (int i = 0; i < rows; i++) {
            TableColumn column = tableColumnModel.getColumn(i);
            JLabel labelTBH = new JLabel(tbVals[i], JLabel.CENTER);
            labelTBH.setFont(tbHFont);
            if (i == (rows - 1)) {
                labelTBH.setBorder(new MatteBorder(0, 0, 1, 0, c));
            } else {
                labelTBH.setBorder(new MatteBorder(0, 0, 1, 1, c));
            }
            labelTBH.setForeground(Color.GRAY);
            column.setHeaderRenderer(renderer);
            column.setHeaderValue(labelTBH);
        }
    }

    public void customTBHEADSP(JTable table, String tbVals[], int rows, Color c) {
        JTableHeader head = table.getTableHeader();
        head.setBackground(new java.awt.Color(252, 252, 252));
        head.setPreferredSize(new Dimension(100, 30));
        TableColumnModel tableColumnModel = head.getColumnModel();
        TableCellRenderer renderer = new JComponentTableCellRenderer();
        Font tbHFont = new Font("Segoe UI", Font.PLAIN, 12);
        for (int i = 0; i < rows; i++) {
            TableColumn column = tableColumnModel.getColumn(i);
            JTextField labelTBH = new JTextField(tbVals[i]);
            labelTBH.setHorizontalAlignment(JTextField.CENTER);
            labelTBH.setFont(tbHFont);
            labelTBH.setBackground(new Color(252, 252, 252));
            if (i == (rows - 1)) {
                labelTBH.setBorder(new MatteBorder(0, 0, 1, 0, c));
            } else {
                labelTBH.setBorder(new MatteBorder(0, 0, 1, 1, c));
            }
            labelTBH.setForeground(Color.GRAY);
            if (i == 3 | i == 4) {
                labelTBH.setBackground(new Color(232, 245, 233));
            }
            if (i == 6 | i == 7) {
                labelTBH.setBackground(new Color(225,245,254));
            }
            column.setHeaderRenderer(renderer);
            column.setHeaderValue(labelTBH);
        }
    }

    public boolean checkEMPTY(JTextField tf[]) {
        boolean rt = false;
        for (int i = 0; i < tf.length; i++) {
            if (tf[i].getText().equals("")) {
                toolt(tf[i], "This field cannot be empty");
                rt = true;
            }
        }
        return rt;
    }

    public void numsONLY(java.awt.event.KeyEvent evt) {
        if (Character.isLetter(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }

    public void clearTFS(JTextField... tf) {
        for (int i = 0; i < tf.length; i++) {
            tf[i].setText("");
        }
    }

    public void clearLBS(JLabel... tf) {
        for (int i = 0; i < tf.length; i++) {
            tf[i].setText("");
        }
    }

    public void INVno(boolean isupdate, JTextField tf, String cat) {
        try {
            ResultSet nosrs = DB.search("SELECT no FROM nos WHERE cat='" + cat + "'");
            int no = 0;
            while (nosrs.next()) {
                no = nosrs.getInt(1);
            }
            if (isupdate) {
                no++;
                DB.Execute("UPDATE nos SET no='" + no + "' WHERE cat='" + cat + "' ");
            }
            tf.setText("" + no);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
