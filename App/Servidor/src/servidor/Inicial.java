package servidor;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inicial extends javax.swing.JFrame 
{
    private final BaseDatos bd;
    
    public Inicial() 
    {
        initComponents();
        IP();
        this.bd = new BaseDatos();
        
        Servidor ser = new Servidor();
        Thread hSer = new Thread(ser);
        hSer.start();
        
        cmbDia.removeAllItems();
        cmbHora.removeAllItems();
        cmbMinuto.removeAllItems();
        cmbTiempo.removeAllItems();
        
        String[] dias;
        dias = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        for (int i = 0; i < 7; i++)
            cmbDia.addItem(dias[i]);
        
        cmbHora.addItem("12");
        for (int i = 1; i < 12; i++)
            cmbHora.addItem("" + i);
        
        cmbMinuto.addItem("00");
        cmbMinuto.addItem("30");
        
        cmbTiempo.addItem("am");
        cmbTiempo.addItem("pm");
        
        //cargarDatos();
    }
    
    public void IP()
    { 
        // Returns the instance of InetAddress containing 
        // local host name and address 
        InetAddress localhost = null; 
        try 
        {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtIpPrivada.setText("" + localhost.getHostAddress().trim()); 
  
        // Find public IP address 
        String systemipaddress = "";
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (IOException e) 
        { 
            systemipaddress = "Cannot Execute Properly"; 
        } 
        txtIpPublica.setText("" + systemipaddress); 
    }
    
    public void cargarDatos()
    {
        String archivo = "Base de datos clientes MSS.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                System.out.println("" + line);
                
                StringTokenizer tokens = new StringTokenizer(line, ",");
                String cNombre = tokens.nextToken();
                int cMaximo = Integer.parseInt(tokens.nextToken());
                int cTipo = Integer.parseInt(tokens.nextToken());
                
                System.out.println("Nombre = " + cNombre + ". Tipo = " + cTipo 
                + ". Maximo = " + cMaximo);
                
                StringTokenizer tokensNombre = new StringTokenizer(cNombre);
                String cUsuario = "" + tokensNombre.nextToken() + "." 
                        + tokensNombre.nextToken();
                
                System.out.println("Usuario = " + cUsuario + ". Tipo = " + cTipo 
                + ". Maximo = " + cMaximo);
                
                bd.insertarCliente(cUsuario, "", cTipo, cMaximo, 0);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Archivo no encontrado");
        }
        catch(IOException e) {}
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIpPrivada = new javax.swing.JTextField();
        txtIpPublica = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtTipoCliente = new javax.swing.JTextField();
        txtMaximo = new javax.swing.JTextField();
        btnAgregarCliente = new javax.swing.JButton();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTipoClase = new javax.swing.JTextField();
        cmbDia = new javax.swing.JComboBox<>();
        cmbHora = new javax.swing.JComboBox<>();
        cmbMinuto = new javax.swing.JComboBox<>();
        cmbTiempo = new javax.swing.JComboBox<>();
        btnAgregarClase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor MSS");

        jLabel1.setText("IP privada");

        txtIpPrivada.setEditable(false);
        txtIpPrivada.setName(""); // NOI18N

        txtIpPublica.setEditable(false);
        txtIpPublica.setName(""); // NOI18N

        jLabel2.setText("IP publica");

        jInternalFrame1.setTitle("Clientes");
        jInternalFrame1.setVisible(true);

        jLabel3.setText("Usuario");

        jLabel4.setText("Tipo");

        jLabel5.setText("Maximo");

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        txtTipoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyTyped(evt);
            }
        });

        txtMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaximoKeyTyped(evt);
            }
        });

        btnAgregarCliente.setText("Agregar");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarCliente)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTipoCliente)
                                .addComponent(txtMaximo)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarCliente)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jInternalFrame2.setTitle("Clases");
        jInternalFrame2.setMinimumSize(new java.awt.Dimension(50, 34));
        jInternalFrame2.setPreferredSize(new java.awt.Dimension(270, 209));
        jInternalFrame2.setVisible(true);

        jLabel6.setText("Dia");

        jLabel7.setText("Hora");

        jLabel9.setText("Tipo");

        txtTipoClase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoClaseKeyTyped(evt);
            }
        });

        cmbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMinuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbTiempo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarClase.setText("Agregar");
        btnAgregarClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21)
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTipoClase)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarClase))
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(255, 255, 255))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTipoClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarClase)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIpPublica, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIpPrivada, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jInternalFrame1)
                    .addComponent(jInternalFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIpPrivada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIpPublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        char ch = evt.getKeyChar();
        if (!( (txtUsuario.getText().length() < 25) &&
                ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || 
                (ch == 'ñ' || ch == 'Ñ') || (ch >= '0' && ch <= '9') || (ch == '.'))))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtTipoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyTyped
        char ch = evt.getKeyChar();
        if (!( (txtTipoCliente.getText().length() < 1) &&
                (ch >= '0' && ch <= '2')))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtTipoClienteKeyTyped

    private void txtMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyTyped
        char ch = evt.getKeyChar();
        if (!( (txtMaximo.getText().length() < 2) &&
                (ch >= '0' && ch <= '9')))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaximoKeyTyped

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        if (txtUsuario.getText().length() > 0 
                && txtTipoCliente.getText().length() > 0
                && txtMaximo.getText().length() > 0)
        {
            String cUsuario = txtUsuario.getText();
            int cTipo = Integer.parseInt(txtTipoCliente.getText());
            int cMaximo = Integer.parseInt(txtMaximo.getText());

            bd.insertarCliente(cUsuario, "", cTipo, cMaximo, 0);
            
            txtUsuario.setText("");
            txtTipoCliente.setText("");
            txtMaximo.setText("");
        }
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void txtTipoClaseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClaseKeyTyped
        char ch = evt.getKeyChar();
        if (!( (txtTipoClase.getText().length() < 1) &&
            (ch >= '0' && ch <= '2')))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtTipoClaseKeyTyped

    private void btnAgregarClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClaseActionPerformed
        if (txtTipoClase.getText().length() > 0)
        {
            int cDia = cmbDia.getSelectedIndex();
            int cHora = Integer.parseInt(cmbHora.getSelectedItem().toString());
            int cMin = Integer.parseInt(cmbMinuto.getSelectedItem().toString());;
            int cTipo = Integer.parseInt(txtTipoClase.getText());
            String cTiempo = cmbTiempo.getSelectedItem().toString();
            
            if(cTiempo.equals("am") && cHora == 12)
                cHora = 0;
            else
            {
                if (cTiempo.equals("pm") && cHora < 12)
                    cHora += 12;
            }
            
            bd.insertarClase(cDia, cHora, cMin, cTipo, 15, 0);
            
            txtTipoClase.setText("");
            cmbDia.setSelectedIndex(0);
            cmbHora.setSelectedIndex(0);
            cmbMinuto.setSelectedIndex(0);
            cmbTiempo.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnAgregarClaseActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarClase;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JComboBox<String> cmbDia;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JComboBox<String> cmbMinuto;
    private javax.swing.JComboBox<String> cmbTiempo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtIpPrivada;
    private javax.swing.JTextField txtIpPublica;
    private javax.swing.JTextField txtMaximo;
    private javax.swing.JTextField txtTipoClase;
    private javax.swing.JTextField txtTipoCliente;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
