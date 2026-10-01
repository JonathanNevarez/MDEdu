/**
 */
package com.project.mde.programming;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sensor Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Consultas abstractas al entorno, sin ejecución ni representación visual.
 * <!-- end-model-doc -->
 * @see com.project.mde.programming.ProgrammingPackage#getSensorKind()
 * @model
 * @generated
 */
public enum SensorKind implements Enumerator {
	/**
	 * The '<em><b>FRONT CLEAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FRONT_CLEAR_VALUE
	 * @generated
	 * @ordered
	 */
	FRONT_CLEAR(0, "FRONT_CLEAR", "FRONT_CLEAR"),

	/**
	 * The '<em><b>LEFT CLEAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_CLEAR_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_CLEAR(1, "LEFT_CLEAR", "LEFT_CLEAR"),

	/**
	 * The '<em><b>RIGHT CLEAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_CLEAR_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_CLEAR(2, "RIGHT_CLEAR", "RIGHT_CLEAR"),

	/**
	 * The '<em><b>AT GOAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AT_GOAL_VALUE
	 * @generated
	 * @ordered
	 */
	AT_GOAL(3, "AT_GOAL", "AT_GOAL"),

	/**
	 * The '<em><b>HAS KEY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HAS_KEY_VALUE
	 * @generated
	 * @ordered
	 */
	HAS_KEY(4, "HAS_KEY", "HAS_KEY"),

	/**
	 * The '<em><b>ON KEY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON_KEY_VALUE
	 * @generated
	 * @ordered
	 */
	ON_KEY(5, "ON_KEY", "ON_KEY"),

	/**
	 * The '<em><b>DOOR AHEAD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOOR_AHEAD_VALUE
	 * @generated
	 * @ordered
	 */
	DOOR_AHEAD(6, "DOOR_AHEAD", "DOOR_AHEAD"),

	/**
	 * The '<em><b>DOOR OPEN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOOR_OPEN_VALUE
	 * @generated
	 * @ordered
	 */
	DOOR_OPEN(7, "DOOR_OPEN", "DOOR_OPEN");

	/**
	 * The '<em><b>FRONT CLEAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FRONT_CLEAR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FRONT_CLEAR_VALUE = 0;

	/**
	 * The '<em><b>LEFT CLEAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_CLEAR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_CLEAR_VALUE = 1;

	/**
	 * The '<em><b>RIGHT CLEAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_CLEAR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_CLEAR_VALUE = 2;

	/**
	 * The '<em><b>AT GOAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AT_GOAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AT_GOAL_VALUE = 3;

	/**
	 * The '<em><b>HAS KEY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HAS_KEY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HAS_KEY_VALUE = 4;

	/**
	 * The '<em><b>ON KEY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON_KEY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ON_KEY_VALUE = 5;

	/**
	 * The '<em><b>DOOR AHEAD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOOR_AHEAD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DOOR_AHEAD_VALUE = 6;

	/**
	 * The '<em><b>DOOR OPEN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOOR_OPEN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DOOR_OPEN_VALUE = 7;

	/**
	 * An array of all the '<em><b>Sensor Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SensorKind[] VALUES_ARRAY =
		new SensorKind[] {
			FRONT_CLEAR,
			LEFT_CLEAR,
			RIGHT_CLEAR,
			AT_GOAL,
			HAS_KEY,
			ON_KEY,
			DOOR_AHEAD,
			DOOR_OPEN,
		};

	/**
	 * A public read-only list of all the '<em><b>Sensor Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SensorKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Sensor Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SensorKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SensorKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sensor Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SensorKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SensorKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sensor Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SensorKind get(int value) {
		switch (value) {
			case FRONT_CLEAR_VALUE: return FRONT_CLEAR;
			case LEFT_CLEAR_VALUE: return LEFT_CLEAR;
			case RIGHT_CLEAR_VALUE: return RIGHT_CLEAR;
			case AT_GOAL_VALUE: return AT_GOAL;
			case HAS_KEY_VALUE: return HAS_KEY;
			case ON_KEY_VALUE: return ON_KEY;
			case DOOR_AHEAD_VALUE: return DOOR_AHEAD;
			case DOOR_OPEN_VALUE: return DOOR_OPEN;
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
	private SensorKind(int value, String name, String literal) {
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
	
} //SensorKind
