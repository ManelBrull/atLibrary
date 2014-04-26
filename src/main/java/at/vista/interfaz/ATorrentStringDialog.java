package at.vista.interfaz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class ATorrentStringDialog extends ATorrentDialog {

	protected String result;
	protected Shell shell;
	protected String contLabel;
	protected int width;
	private Font labelFont;
	private Label label;
	private Text text;
	private Button btnNuevo;
	private Button btnSalir;
	
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ATorrentStringDialog(Shell parent, String title, String textoAMostrar, int width) {
		super(parent, title);
		this.width = width;
		contLabel = textoAMostrar;
		labelFont = SWTResourceManager.getFont(
				Recursos.nombreFuenteLabelNormal,
				Recursos.tamanoFuenteLabelNormal,
				Recursos.estiloFuenteLabelNormal);
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public String open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL);
		super.createContents(shell);
		shell.setSize(width, 125);
		
		label = new Label(shell, SWT.NONE);
		label.setBounds(10, 10, width-25, 19);
		label.setFont(labelFont);
		label.setForeground(getAzul());
		label.setText(this.contLabel);
		
		text = new Text(shell, SWT.SINGLE | SWT.BORDER);
		text.setBounds(10, 35, width-25, 19);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR){
					result = text.getText().trim();
					shell.dispose();
				}
			}
		});
		
		btnNuevo = new Button(shell, SWT.PUSH);
		btnNuevo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = text.getText().trim();
				shell.dispose();
			}
		});
		btnNuevo.setBounds(width-161, 60, 68, 23);
		btnNuevo.setText("Aceptar");
		
		Button btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = null;
				shell.dispose();
			}
		});
		btnCancelar.setBounds(width-83, 60, 68, 23);
		btnCancelar.setText("Cancelar");

	}

	@Override
	public void myDispose() {
		// TODO Auto-generated method stub
		labelFont.dispose();
	}

}
