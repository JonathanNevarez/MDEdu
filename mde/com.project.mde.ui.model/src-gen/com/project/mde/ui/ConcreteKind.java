/**
 */
package com.project.mde.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Concrete Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiPackage#getConcreteKind()
 * @model
 * @generated
 */
public enum ConcreteKind implements Enumerator {
	/**
	 * The '<em><b>BLOCKLY EDITOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCKLY_EDITOR_VALUE
	 * @generated
	 * @ordered
	 */
	BLOCKLY_EDITOR(0, "BLOCKLY_EDITOR", "BLOCKLY_EDITOR"),

	/**
	 * The '<em><b>GRIDWORLD VIEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GRIDWORLD_VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	GRIDWORLD_VIEW(1, "GRIDWORLD_VIEW", "GRIDWORLD_VIEW"),

	/**
	 * The '<em><b>INSTRUCTION PANEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTRUCTION_PANEL_VALUE
	 * @generated
	 * @ordered
	 */
	INSTRUCTION_PANEL(2, "INSTRUCTION_PANEL", "INSTRUCTION_PANEL"),

	/**
	 * The '<em><b>FEEDBACK PANEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEEDBACK_PANEL_VALUE
	 * @generated
	 * @ordered
	 */
	FEEDBACK_PANEL(3, "FEEDBACK_PANEL", "FEEDBACK_PANEL"),

	/**
	 * The '<em><b>HINT PANEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HINT_PANEL_VALUE
	 * @generated
	 * @ordered
	 */
	HINT_PANEL(4, "HINT_PANEL", "HINT_PANEL"),

	/**
	 * The '<em><b>NAVIGATION PANEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAVIGATION_PANEL_VALUE
	 * @generated
	 * @ordered
	 */
	NAVIGATION_PANEL(5, "NAVIGATION_PANEL", "NAVIGATION_PANEL"),

	/**
	 * The '<em><b>CODE PANEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CODE_PANEL_VALUE
	 * @generated
	 * @ordered
	 */
	CODE_PANEL(6, "CODE_PANEL", "CODE_PANEL"),

	/**
	 * The '<em><b>LUMA TUTOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LUMA_TUTOR_VALUE
	 * @generated
	 * @ordered
	 */
	LUMA_TUTOR(7, "LUMA_TUTOR", "LUMA_TUTOR");

	/**
	 * The '<em><b>BLOCKLY EDITOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCKLY_EDITOR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKLY_EDITOR_VALUE = 0;

	/**
	 * The '<em><b>GRIDWORLD VIEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GRIDWORLD_VIEW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GRIDWORLD_VIEW_VALUE = 1;

	/**
	 * The '<em><b>INSTRUCTION PANEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTRUCTION_PANEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INSTRUCTION_PANEL_VALUE = 2;

	/**
	 * The '<em><b>FEEDBACK PANEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEEDBACK_PANEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FEEDBACK_PANEL_VALUE = 3;

	/**
	 * The '<em><b>HINT PANEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HINT_PANEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HINT_PANEL_VALUE = 4;

	/**
	 * The '<em><b>NAVIGATION PANEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAVIGATION_PANEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NAVIGATION_PANEL_VALUE = 5;

	/**
	 * The '<em><b>CODE PANEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CODE_PANEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CODE_PANEL_VALUE = 6;

	/**
	 * The '<em><b>LUMA TUTOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LUMA_TUTOR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LUMA_TUTOR_VALUE = 7;

	/**
	 * An array of all the '<em><b>Concrete Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ConcreteKind[] VALUES_ARRAY =
		new ConcreteKind[] {
			BLOCKLY_EDITOR,
			GRIDWORLD_VIEW,
			INSTRUCTION_PANEL,
			FEEDBACK_PANEL,
			HINT_PANEL,
			NAVIGATION_PANEL,
			CODE_PANEL,
			LUMA_TUTOR,
		};

	/**
	 * A public read-only list of all the '<em><b>Concrete Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ConcreteKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Concrete Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ConcreteKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConcreteKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Concrete Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ConcreteKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConcreteKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Concrete Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ConcreteKind get(int value) {
		switch (value) {
			case BLOCKLY_EDITOR_VALUE: return BLOCKLY_EDITOR;
			case GRIDWORLD_VIEW_VALUE: return GRIDWORLD_VIEW;
			case INSTRUCTION_PANEL_VALUE: return INSTRUCTION_PANEL;
			case FEEDBACK_PANEL_VALUE: return FEEDBACK_PANEL;
			case HINT_PANEL_VALUE: return HINT_PANEL;
			case NAVIGATION_PANEL_VALUE: return NAVIGATION_PANEL;
			case CODE_PANEL_VALUE: return CODE_PANEL;
			case LUMA_TUTOR_VALUE: return LUMA_TUTOR;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ConcreteKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ConcreteKind
