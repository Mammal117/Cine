package com.raven.component;

import com.raven.connection.DatabaseOperation;
import com.raven.model.Model_Card;
import com.raven.model.Pelicula;
import com.raven.swing.EditButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Card extends javax.swing.JPanel {

    static DatabaseOperation db;

    private final Model_Card data;
    private final Timer timer;
    private final Timer timerStop;
    private final CardDescription cardDescription;
    private int y = 140;
    private int speed = 2;
    private boolean showing = false;

    public Card(Model_Card data) {

        db = new DatabaseOperation();

        this.data = data;
        initComponents();
        setOpaque(false);
        setPreferredSize(new Dimension(150, 200));
        cardDescription = new CardDescription(data.getTitle(), data.getDescription());
        cardDescription.setLocation(0, y);
        cardDescription.setSize(new Dimension(150, 150));
        add(cardDescription);
        add(edit);
        edit.setBounds(10, 10, 35, 35); // Adjust X, Y, width, height
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (showing) {
                    y -= speed;
                    if (y <= 50) {
                        y = 50;
                        cardDescription.setLocation(0, y);
                        timer.stop();
                    } else {
                        cardDescription.setLocation(0, y);
                    }
                } else {
                    y += speed;
                    if (y >= 140) {
                        y = 140;
                        cardDescription.setLocation(0, y);
                        timer.stop();
                    } else {
                        cardDescription.setLocation(0, y);
                    }
                }
            }
        });
        //  500 for delay hide description
        timerStop = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showing = false;
                timerStop.stop();
                timer.start();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                showing = true;
                timerStop.stop();
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                timerStop.start();
            }
        });
    }

    public EditButton getEdit() {
        return edit;
    }

    // Método para actualizar el icono del Card (y por tanto la imagen que se dibuja en el paintComponent)
    public void updateIcon(Icon newIcon) {
        data.setIcon(newIcon);  // Actualiza el icono en el Model_Card
        repaint(); // Repinta la tarjeta con el nuevo icono
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edit = new com.raven.swing.EditButton();

        setLayout(null);

        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit);
        edit.setBounds(300, 40, 40, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        new Pelicula().editPoster(data.getIdPelicula(), this, edit);
    }//GEN-LAST:event_editActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        Rectangle size = getAutoSize(data.getIcon());
        g2.drawImage(toImage(data.getIcon()), size.x, size.y, size.width, size.height, null);
        super.paintComponent(grphcs);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, getHeight(), new Color(64, 67, 186, 200), 0, getHeight() - 50, new Color(0, 0, 0, 0));
        g2.setPaint(g);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private Rectangle getAutoSize(Icon image) {
        int w = 150;
        int h = 200;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.EditButton edit;
    // End of variables declaration//GEN-END:variables
}
