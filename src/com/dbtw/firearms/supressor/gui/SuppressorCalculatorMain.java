package com.dbtw.firearms.supressor.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.dbtw.firearms.supressor.Caliber;
import com.dbtw.firearms.supressor.Chamber;
import com.dbtw.firearms.supressor.Suppressor;
import com.dbtw.firearms.supressor.SuppressorCalculator;
import com.dbtw.firearms.supressor.calculation.Pressure;
import com.dbtw.widgets.DebugWriter;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class SuppressorCalculatorMain extends JFrame {
  private JTextField txtBarrelLength;
  private JTable tblChambers;
  private JTable tblSuppression;
  private DefaultTableModel modelChambers;
  private DefaultTableModel modelSuppression;
  private JComboBox<Caliber> cboCaliber = new JComboBox<Caliber>();

  private Suppressor suppressor = null;
  private Caliber caliber = null;
  private double barrelLength = 0.0;
  private JTextField txtWallThickness;
  private JTextField txtBaffleThickness;
  private JTextField txtCanLength;
  
  
  public SuppressorCalculatorMain() {
    setTitle("Suppressor Calculator");
    getContentPane().setLayout(null);
    
    JLabel lblCaliber = new JLabel("Caliber");
    lblCaliber.setHorizontalAlignment(SwingConstants.RIGHT);
    lblCaliber.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblCaliber.setBounds(10, 11, 132, 14);
    getContentPane().add(lblCaliber);
    
    JLabel lblBarrelLength = new JLabel("Barrel Length");
    lblBarrelLength.setHorizontalAlignment(SwingConstants.RIGHT);
    lblBarrelLength.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblBarrelLength.setBounds(10, 36, 132, 14);
    getContentPane().add(lblBarrelLength);
    
    JLabel lblSuppressorStructure = new JLabel("Suppressor Structure");
    lblSuppressorStructure.setHorizontalAlignment(SwingConstants.RIGHT);
    lblSuppressorStructure.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblSuppressorStructure.setBounds(10, 72, 132, 14);
    getContentPane().add(lblSuppressorStructure);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(23, 97, 271, 385);
    getContentPane().add(scrollPane);
    
    tblChambers = new JTable();
    scrollPane.setViewportView(tblChambers);
    
    txtBarrelLength = new JTextField();
    txtBarrelLength.setBounds(152, 33, 86, 20);
    getContentPane().add(txtBarrelLength);
    txtBarrelLength.setColumns(10);
    
    JLabel lblSuppression = new JLabel("Suppression");
    lblSuppression.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblSuppression.setBounds(394, 72, 101, 14);
    getContentPane().add(lblSuppression);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(394, 97, 271, 383);
    getContentPane().add(scrollPane_1);
    
    tblSuppression = new JTable();
    scrollPane_1.setViewportView(tblSuppression);
    
    cboCaliber.setBounds(152, 7, 86, 22);
    getContentPane().add(cboCaliber);
    
    JLabel lblNewLabel = new JLabel("Wall Thickness");
    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel.setBounds(417, 14, 101, 14);
    getContentPane().add(lblNewLabel);
    
    JLabel lblNewLabel_1 = new JLabel("Baffle Thickness");
    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_1.setBounds(399, 39, 119, 14);
    getContentPane().add(lblNewLabel_1);
    
    txtWallThickness = new JTextField();
    txtWallThickness.setBounds(528, 11, 86, 20);
    getContentPane().add(txtWallThickness);
    txtWallThickness.setColumns(10);
    
    txtBaffleThickness = new JTextField();
    txtBaffleThickness.setBounds(528, 35, 86, 20);
    getContentPane().add(txtBaffleThickness);
    txtBaffleThickness.setColumns(10);
    
    JLabel lblSilencerLength = new JLabel("Silencer Length");
    lblSilencerLength.setHorizontalAlignment(SwingConstants.RIGHT);
    lblSilencerLength.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblSilencerLength.setBounds(135, 508, 103, 14);
    getContentPane().add(lblSilencerLength);
    
    txtCanLength = new JTextField();
    txtCanLength.setBounds(249, 505, 86, 20);
    getContentPane().add(txtCanLength);
    txtCanLength.setColumns(10);
    
    init();
  }
  
  public void center() {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int w = this.getSize().width;
    int h = this.getSize().height;
    int x = (dim.width - w) / 2;
    int y = (dim.height - h) / 2;
    this.setLocation(x, y);
  }
  
  private void init() {
    center();

    loadCalibers();
    
    
    cboCaliber.setActionCommand("Caliber");
    cboCaliber.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getSource() == cboCaliber) {
          loadGun();
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
      }
      
    });
    cboCaliber.setSelectedIndex(0);
    caliber = Caliber.valueOf(cboCaliber.getSelectedItem().toString());
    
    suppressor = new Suppressor(caliber);
    
    txtBarrelLength.setText("18.0");
    txtBarrelLength.setActionCommand("BarrelLength");
    txtBarrelLength.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("BarrelLength")) {
          barrelLength = Double.parseDouble(txtBarrelLength.getText());
        }
      }
    });
    
    createChamberModel();
    createCalculationModel();
    initializeSuppressor();
    
  }

  private void addChamber(Chamber chamber) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    addChamber");
      DebugWriter.getInstance().write("        received: " + chamber.toString());
    }
    Vector<String> values = new Vector<String>();
    
    values.add(Double.toString(chamber.getLength()));
    values.add(Double.toString(chamber.getDiameter()));
    values.add(Double.toString(chamber.getVolume()));
    
    modelChambers.addRow(values);
  }
  
  private void calculate() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    calculate");
    }
    double pressure = caliber.getChamberPressure();
    
    createCalculationModel();
    Vector<String> values = new Vector<String>();
    values.add("Chamber");
    values.add(Double.toString(pressure));
  
    for (int i = 0; i < modelChambers.getRowCount(); i++) {
      for (int j = 0; j < modelChambers.getColumnCount(); j++) {
        values = new Vector<String>();
        values.add("Row " + i);
        
//        double newPressure = Pressure.pressureChange(pressure, volume, changedValue, changedParameter)
      }
    }
  }
  
  private void loadGun() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    loadGun");
      DebugWriter.getInstance().write("        setting cal to " + cboCaliber.getSelectedItem().toString());
      DebugWriter.getInstance().write("        setting blen to " + txtBarrelLength.getText());
    }
    String cal = cboCaliber.getSelectedItem().toString();
    String blen = txtBarrelLength.getText();
    
    if ((blen != null) && (blen.length() > 0)) {
      if (SuppressorCalculator.debug) {
        DebugWriter.getInstance().write(this.getClass().getName());
        DebugWriter.getInstance().write("    loadGun");
        DebugWriter.getInstance().write("        setting len to " + txtBarrelLength.getText());
      }
      String len = txtBarrelLength.getText();
    
      barrelLength = Double.valueOf(len);
    }
    
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    loadGun");
      DebugWriter.getInstance().write("        returning caliber");
    }
    caliber = Caliber.valueOf(cal);
  }
  
  private void createChamberModel() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    createChamberModel");
    }
    modelChambers = new DefaultTableModel();
    
    Vector<String> chamberHeader = new Vector<String>();
    chamberHeader.add("Chamber");
    chamberHeader.add("Length");
    chamberHeader.add("Add");
    
    modelChambers.setColumnCount(3);
    modelChambers.setColumnIdentifiers(chamberHeader);
  }

  private void createCalculationModel() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    createCalculationModel");
    }
    modelSuppression = new DefaultTableModel();
    
    Vector<String> calculationHeader = new Vector<String>();
    calculationHeader.add("Chamber");
    calculationHeader.add("End Pressure");
    
    modelSuppression.setColumnCount(2);
    modelSuppression.setColumnIdentifiers(calculationHeader);
  }
  
  private void addChamberRow() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    addChamberRow");
    }
    DefaultTableModel newModel = new DefaultTableModel();
    newModel.setColumnCount(3);
    
    newModel.setNumRows(modelChambers.getRowCount() + 1);
    Object[] values = new Object[3];
    
    
    JButton btn = new JButton("+");
    btn.setActionCommand("addRow");
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addRow")) {
          addChamberRow();
        }
      }
      
    });

    for (int i = 0; i < modelChambers.getRowCount(); i++) {
      newModel.setValueAt(modelChambers.getValueAt(i, 0), i, 0);
      newModel.setValueAt(modelChambers.getValueAt(i, 1), i, 1);
      newModel.setValueAt(modelChambers.getValueAt(i, 2), i, 2);
    }
    
    values[0] = Integer.toString(modelChambers.getRowCount());
    values[1] = Double.toString(0.0);
    values[2] = btn;
  }
  
  private void readSuppressor() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    readSuppressor");
    }
    cboCaliber.setSelectedItem(caliber.getDescription());
    txtWallThickness.setText(Double.toString(suppressor.getWallThickness()));
    txtBaffleThickness.setText(Double.toString(suppressor.getBaffleThickness()));
    createChamberModel();
    modelChambers.setRowCount(suppressor.getChamberCount());
    
    for (int i = 0; i < suppressor.getChamberCount(); i++) {
      modelChambers.setValueAt(Integer.toString(i), i, 0);
      modelChambers.setValueAt(Double.toString(suppressor.getChamberLength(i)), i, 1);
      JButton btn = new JButton("+");
      btn.setActionCommand("addRow");
      btn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (e.getActionCommand().equals("addRow")) {
            addChamberRow();
          }
        }
        
      });
      modelChambers.setValueAt(btn, i, 2);
    }
    tblChambers.setModel(modelChambers);
  }
  
  private void displaySuppressor() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    displaySuppressor");
      DebugWriter.getInstance().write("        Setting caliber to " + Caliber.valueOf(cboCaliber.getSelectedItem().toString()));
      DebugWriter.getInstance().write("        Setting wall thickness to " + Double.parseDouble(txtWallThickness.getText()));
      DebugWriter.getInstance().write("        Setting baffle thickness to " + Double.parseDouble(txtBaffleThickness.getText()));
    }
    suppressor.setCaliber(Caliber.valueOf(cboCaliber.getSelectedItem().toString()));
    suppressor.setWallThickness(Double.parseDouble(txtWallThickness.getText()));
    suppressor.setBaffleThickness(Double.parseDouble(txtBaffleThickness.getText()));
    
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    displaySuppressor");
      DebugWriter.getInstance().write("        Clearing chambers");
    }
    suppressor.clearChambers();
    
    for (int i = 0; i < modelChambers.getRowCount(); i++) {
      if (SuppressorCalculator.debug) {
        DebugWriter.getInstance().write(this.getClass().getName());
        DebugWriter.getInstance().write("    displaySuppressor");
        DebugWriter.getInstance().write("        Adding a chamber of length " + Double.valueOf(modelChambers.getValueAt(i, 1).toString()));
      }
      suppressor.addChamber(Double.valueOf(modelChambers.getValueAt(i, 1).toString()));
    }
  }

  private void loadCalibers() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    loadCalibers");
      DebugWriter.getInstance().write("        Entering into caliber enumeration");
    }
    for (int i = 0; i < Caliber.values().length; i++) {
      if (SuppressorCalculator.debug) {
        DebugWriter.getInstance().write(this.getClass().getName());
        DebugWriter.getInstance().write("    loadCalibers");
        DebugWriter.getInstance().write("        Adding " + Caliber.values()[i].getDescription() + " to combobox");
      }
      cboCaliber.addItem(Caliber.values()[i]);
    }
  }
  
  private void initializeSuppressor() {
    createChamberModel();
    addChamberRow();
    
    modelChambers.setValueAt("0", 0, 0);
    modelChambers.setValueAt(txtBarrelLength, 0, 1);
  }
  
}

