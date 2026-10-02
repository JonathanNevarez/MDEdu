/**
 */
package com.project.mde.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Abstract Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiPackage#getAbstractKind()
 * @model
 * @generated
 */
public enum AbstractKind implements Enumerator {
	/**
	 * The '<em><b>WORKSPACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WORKSPACE_VALUE
	 * @generated
	 * @ordered
	 */
	WORKSPACE(0, "WORKSPACE", "WORKSPACE"),

	/**
	 * The '<em><b>SIMULATION AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIMULATION_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	SIMULATION_AREA(1, "SIMULATION_AREA", "SIMULATION_AREA"),

	/**
	 * The '<em><b>INSTRUCTION AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTRUCTION_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	INSTRUCTION_AREA(2, "INSTRUCTION_AREA", "INSTRUCTION_AREA"),

	/**
	 * The '<em><b>FEEDBACK AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEEDBACK_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	FEEDBACK_AREA(3, "FEEDBACK_AREA", "FEEDBACK_AREA"),

	/**
	 * The '<em><b>HINT AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HINT_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	HINT_AREA(4, "HINT_AREA", "HINT_AREA"),

	/**
	 * The '<em><b>NAVIGATION AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAVIGATION_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	NAVIGATION_AREA(5, "NAVIGATION_AREA", "NAVIGATION_AREA"),

	/**
	 * The '<em><b>CODE AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CODE_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	CODE_AREA(6, "CODE_AREA", "CODE_AREA"),

	/**
	 * The '<em><b>TUTOR AREA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TUTOR_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	TUTOR_AREA(7, "TUTOR_AREA", "TUTOR_AREA");

	/**
	 * The '<em><b>WORKSPACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WORKSPACE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WORKSPACE_VALUE = 0;

	/**
	 * The '<em><b>SIMULATION AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIMULATION_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SIMULATION_AREA_VALUE = 1;

	/**
	 * The '<em><b>INSTRUCTION AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTRUCTION_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INSTRUCTION_AREA_VALUE = 2;

	/**
	 * The '<em><b>FEEDBACK AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEEDBACK_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FEEDBACK_AREA_VALUE = 3;

	/**
	 * The '<em><b>HINT AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HINT_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HINT_AREA_VALUE = 4;

	/**
	 * The '<em><b>NAVIGATION AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAVIGATION_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NAVIGATION_AREA_VALUE = 5;

	/**
	 * The '<em><b>CODE AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CODE_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CODE_AREA_VALUE = 6;

	/**
	 * The '<em><b>TUTOR AREA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TUTOR_AREA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TUTOR_AREA_VALUE = 7;

	/**
	 * An array of all the '<em><b>Abstract Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AbstractKind[] VALUES_ARRAY =
		new AbstractKind[] {
			WORKSPACE,
			SIMULATION_AREA,
			INSTRUCTION_AREA,
			FEEDBACK_AREA,
			HINT_AREA,
			NAVIGATION_AREA,
			CODE_AREA,
			TUTOR_AREA,
		};

	/**
	 * A public read-only list of all the '<em><b>Abstract Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AbstractKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Abstract Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AbstractKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AbstractKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Abstract Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AbstractKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AbstractKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Abstract Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AbstractKind get(int value) {
		switch (value) {
			case WORKSPACE_VALUE: return WORKSPACE;
			case SIMULATION_AREA_VALUE: return SIMULATION_AREA;
			case INSTRUCTION_AREA_VALUE: return INSTRUCTION_AREA;
			case FEEDBACK_AREA_VALUE: return FEEDBACK_AREA;
			case HINT_AREA_VALUE: return HINT_AREA;
			case NAVIGATION_AREA_VALUE: return NAVIGATION_AREA;
			case CODE_AREA_VALUE: return CODE_AREA;
			case TUTOR_AREA_VALUE: return TUTOR_AREA;
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
	private AbstractKind(int value, String name, String literal) {
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
	
} //AbstractKind
