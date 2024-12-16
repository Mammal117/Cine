package com.raven.component;

import com.raven.model.Model_Card;
import com.raven.model.Pelicula;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CardMovie extends javax.swing.JPanel {

    private Color color1;
    private Color color2;
    int id;

    public CardMovie() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
        setPreferredSize(new Dimension(200, 250));
        //lbIcon.setPreferredSize(new java.awt.Dimension(150, 200)); // Set preferred size for lbIcon // ONLY WITH DEFAULT SIZE // Elements can't overlap
        //jButton1.setPreferredSize(new java.awt.Dimension(100, 50));
        lbIcon.setBounds(0, 0, 200, 250);  // Set both position and size for lbIcon
        editButton1.setBounds(155, 15, 35, 30);  // Position the button on top of the icon
        // Initialize the button
        //jButton1 = new JButton("Click Me");
        //editButton.setText("c");

        //         Make the button transparent (so the icon underneath is visible)
        //jButton1.setOpaque(false);
        //jButton1.setContentAreaFilled(false);
        //jButton1.setBorderPainted(false);
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setData(Model_Card data) {
        lbIcon.setIcon(resizeIcon(data.getIcon()));
        this.id = data.getIdPelicula();

    }

    // This method resizes the image icon to a fixed width and height
    private Icon resizeIcon(Icon icon) {
        Image img = ((ImageIcon) icon).getImage();
        Image resizedImg = img.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editButton1 = new com.raven.swing.EditButton();
        lbIcon = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(286, 199));
        setLayout(null);

        editButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton1ActionPerformed(evt);
            }
        });
        add(editButton1);
        editButton1.setBounds(170, 50, 20, 20);

        lbIcon.setBackground(new java.awt.Color(51, 51, 255));
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/testing/15.jpg"))); // NOI18N
        lbIcon.setOpaque(true);
        add(lbIcon);
        lbIcon.setBounds(10, 10, 120, 140);
    }// </editor-fold>//GEN-END:initComponents

    private void editButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton1ActionPerformed
        // TODO add your handling code here:
        new Pelicula().uploadPoster(id, lbIcon);
    }//GEN-LAST:event_editButton1ActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.EditButton editButton1;
    private javax.swing.JLabel lbIcon;
    // End of variables declaration//GEN-END:variables
}
