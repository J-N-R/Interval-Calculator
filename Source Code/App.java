import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import intervals.Interval;

public class App {

	protected Shell shell;
	private Text lowT1;
	private Text highT1;
	private Text lowT2;
	private Text highT2;
	
	private Label Answer_Label;
	private Label Operation;
	private Label Second_Interval_Label;
	
	private char operator;
	
	private Interval first;
	private Interval second;

	private Label DECO1;
	private Label DECO2;
	private Label DECO3;
	private Label DECO4;
	private Label DECO5;
	private Label DECO6;
	private Label First_Interval_Label;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			App window = new App();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(465, 325);
		shell.setText("Interval Calculator");
		
		
// TEXT BOXES
		lowT1 = new Text(shell, SWT.BORDER);
		lowT1.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		lowT1.setBounds(40, 65, 55, 55);
		
		highT1 = new Text(shell, SWT.BORDER);
		highT1.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		highT1.setBounds(130, 65, 55, 55);
		
		lowT2 = new Text(shell, SWT.BORDER);
		lowT2.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		lowT2.setBounds(264, 65, 55, 55);
		
		highT2 = new Text(shell, SWT.BORDER);
		highT2.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		highT2.setBounds(354, 65, 55, 55);
		
		
// DECORATIONS
		Label CALCULATOR_DECO = new Label(shell, SWT.CENTER);
		CALCULATOR_DECO.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		CALCULATOR_DECO.setBounds(93, 10, 260, 37);
		CALCULATOR_DECO.setText("Interval Calculator");
		
		Label AUTHOR_DECO = new Label(shell, SWT.NONE);
		AUTHOR_DECO.setBounds(340, 265, 91, 15);
		AUTHOR_DECO.setText("Jonathan Rivera");
		
		DECO1 = new Label(shell, SWT.CENTER);
		DECO1.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		DECO1.setBounds(96, 75, 33, 65);
		DECO1.setText(",");
		
		DECO2 = new Label(shell, SWT.CENTER);
		DECO2.setText(",");
		DECO2.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		DECO2.setBounds(320, 75, 33, 64);
		
		DECO3 = new Label(shell, SWT.NONE);
		DECO3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		DECO3.setFont(SWTResourceManager.getFont("Segoe UI", 50, SWT.NORMAL));
		DECO3.setBounds(10, 38, 33, 89);
		DECO3.setText("[");
		
		DECO4 = new Label(shell, SWT.NONE);
		DECO4.setText("[");
		DECO4.setFont(SWTResourceManager.getFont("Segoe UI", 50, SWT.NORMAL));
		DECO4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		DECO4.setBounds(233, 38, 33, 89);
		
		DECO5 = new Label(shell, SWT.NONE);
		DECO5.setText("]");
		DECO5.setFont(SWTResourceManager.getFont("Segoe UI", 50, SWT.NORMAL));
		DECO5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		DECO5.setBounds(193, 38, 33, 89);
		
		DECO6 = new Label(shell, SWT.NONE);
		DECO6.setText("]");
		DECO6.setFont(SWTResourceManager.getFont("Segoe UI", 50, SWT.NORMAL));
		DECO6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		DECO6.setBounds(417, 38, 33, 89);
		
		Label ANSWER_DECO = new Label(shell, SWT.CENTER);
		ANSWER_DECO.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		ANSWER_DECO.setBounds(198, 263, 55, 19);
		ANSWER_DECO.setText("Answer");
		
		Label VERSION_DECO = new Label(shell, SWT.NONE);
		VERSION_DECO.setBounds(11, 265, 62, 15);
		VERSION_DECO.setText("Version 1.5");
		
		
// BUTTONS
		Button Add_Button = new Button(shell, SWT.NONE);
		Add_Button.setBounds(40, 140, 55, 25);
		Add_Button.setText("Add");
		
		Button Subtract_Button = new Button(shell, SWT.NONE);
		Subtract_Button.setText("Subtract");
		Subtract_Button.setBounds(130, 140, 55, 25);
		
		Button Multiply_Button = new Button(shell, SWT.NONE);
		Multiply_Button.setText("Multiply");
		Multiply_Button.setBounds(264, 140, 55, 25);
		
		Button Divide_Button = new Button(shell, SWT.NONE);
		Divide_Button.setText("Divide");
		Divide_Button.setBounds(354, 140, 55, 25);
		
		Button Union_Button = new Button(shell, SWT.NONE);
		Union_Button.setToolTipText("Union");
		Union_Button.setBounds(97, 140, 31, 25);
		Union_Button.setText("U");
		
		Button Second_Union_Button = new Button(shell, SWT.NONE);
		Second_Union_Button.setToolTipText("Union");
		Second_Union_Button.setText("U");
		Second_Union_Button.setBounds(321, 140, 31, 25);
		
		Button Negative_Button = new Button(shell, SWT.NONE);
		Negative_Button.setToolTipText("Negative");
		Negative_Button.setText("_");
		Negative_Button.setBounds(11, 140, 27, 25);
		
		Button Second_Negative_Button = new Button(shell, SWT.NONE);
		Second_Negative_Button.setToolTipText("Negative");
		Second_Negative_Button.setText("_");
		Second_Negative_Button.setBounds(235, 140, 27, 25);
		
		
// LABELS		
		Answer_Label = new Label(shell, SWT.CENTER);
		Answer_Label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		
		Answer_Label.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.NORMAL));
		Answer_Label.setBounds(40, 215, 369, 45);
		Answer_Label.setText("N/A");
		
		First_Interval_Label = new Label(shell, SWT.LEFT);
		First_Interval_Label.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		First_Interval_Label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		First_Interval_Label.setBounds(10, 172, 264, 44);
		
		Second_Interval_Label = new Label(shell, SWT.RIGHT);
		Second_Interval_Label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		Second_Interval_Label.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		Second_Interval_Label.setBounds(167, 172, 264, 44);
		
		Operation = new Label(shell, SWT.CENTER);
		Operation.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		Operation.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		Operation.setBounds(40, 172, 369, 44);
		
		
// BUTTON IMPLEMENTATIONS
		// UNION Button
		Union_Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(first != null && first.hasUnion())		return;
				
				try { first = new Interval(Double.parseDouble(lowT1.getText()), Double.parseDouble(highT1.getText())); } 
				
				catch (Exception E) {
					MessageDialog.openError(shell, "Error","Bad first interval");
					return;
				}
				
				lowT1.setText("");
				highT1.setText("");
				Operation.setText("");
				First_Interval_Label.setText(first.toString() + " U [...]");
				First_Interval_Label.setVisible(true);
			}
		});
		
		// SECOND UNION Button
		Second_Union_Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(second != null && second.hasUnion())		return;
						
				try { second = new Interval(Double.parseDouble(lowT2.getText()), Double.parseDouble(highT2.getText())); } 
						
				catch (Exception E) {
					MessageDialog.openError(shell, "Error","Bad second interval");
					return;
				}
						
				lowT2.setText("");
				highT2.setText("");
				Operation.setText("");
				Second_Interval_Label.setText(second.toString() + " U [...]");
				Second_Interval_Label.setVisible(true);
			}
		});
		
		// NEGATIVE Button
				Negative_Button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						try { 	first = new Interval(Double.parseDouble(lowT1.getText()), Double.parseDouble(highT1.getText()));
									first = first.negative();}
						
						catch (Exception E) {
							MessageDialog.openError(shell, "Error","Bad first interval");
							return;
						}	
						
						Operation.setText("");
						First_Interval_Label.setText(first.toString());
						First_Interval_Label.setVisible(true);
						
						// Hide Elements to try not to confuse user
						lowT1.setVisible(false);
						highT1.setVisible(false);
						DECO1.setVisible(false);
						DECO3.setVisible(false);
						DECO5.setVisible(false);
					}
				});
				
		// SECOND NEGATIVE Button
				Second_Negative_Button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						try {	second = new Interval(Double.parseDouble(lowT2.getText()), Double.parseDouble(highT2.getText()));
								second = second.negative();}
				
						catch (Exception E) {
							MessageDialog.openError(shell, "Error","Bad second interval");
							return;
						}	
						
						Operation.setText("");
						Second_Interval_Label.setText(second.toString());
						Second_Interval_Label.setVisible(true);
				
						// Hide Elements to try not to confuse user
						lowT2.setVisible(false);
						highT2.setVisible(false);
						DECO2.setVisible(false);
						DECO4.setVisible(false);
						DECO6.setVisible(false);
					}
				});
		
		// ADD Button
		Add_Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				operator = '+';
				solve("add");
			}
		});
		
		// SUBTRACT Button
		Subtract_Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				operator = '-';
				solve("subtract");
			}
		});
		
		// MULTIPLY Button
		Multiply_Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				operator = '*';
				solve("multiply");
			}
		});
		
		// DIVIDE Button
		Divide_Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				operator = '/';
				try {
					solve("divide");
				} catch (ArithmeticException E) {
					Answer_Label.setText("N/A");
					MessageDialog.openError(shell, "Error","Divide By Zero");
					return;
				}
			}
		});
	}
	
	// Method used by buttons to compute interval operations
	public void solve(String operation) {
		
		// Initialize Intervals with text box text, create a union if interval already exists, completely skip if it exists and has a union
	if(first == null || (first != null && !first.hasUnion()))
		try { first = first == null	?	new Interval(Double.parseDouble(lowT1.getText()), Double.parseDouble(highT1.getText())) 
												:	new Interval(first, Double.parseDouble(lowT1.getText()), Double.parseDouble(highT1.getText())); } 
		
		catch (Exception E) {
			MessageDialog.openError(shell, "Error","Bad first interval");
			return;
		}
	else {
		lowT1.setVisible(true);
		highT1.setVisible(true);
		DECO1.setVisible(true);
		DECO3.setVisible(true);
		DECO5.setVisible(true);
	}
		
		
	if(second == null || (second != null && !second.hasUnion()))
		try { second = second == null ?	new Interval(Double.parseDouble(lowT2.getText()), Double.parseDouble(highT2.getText()))
															:	new Interval(second, Double.parseDouble(lowT2.getText()), Double.parseDouble(highT2.getText()));} 
		
		catch (Exception E) {
			MessageDialog.openError(shell, "Error","Bad second interval");
			return;
		}
	else {
		lowT2.setVisible(true);
		highT2.setVisible(true);
		DECO2.setVisible(true);
		DECO4.setVisible(true);
		DECO6.setVisible(true);
	}
	
		Second_Interval_Label.setVisible(false);
		First_Interval_Label.setVisible(false);
		Operation.setText(first + " " + operator +  " " + second);
		
		Interval answer = null;
		switch(operation) {
			case "add"			:	answer = first.add(second);			break;
			case "subtract"	:	answer = first.subtract(second);	break;
			case "multiply"	:	answer = first.multiply(second);	break;
			case "divide"		:	answer = first.divide(second);		break;
		}
		
		first = null;
		second = null;
		Answer_Label.setText(answer.toString());
		return;
	}
		
}
