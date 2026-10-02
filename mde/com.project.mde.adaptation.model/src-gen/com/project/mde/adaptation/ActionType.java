/**
 */
package com.project.mde.adaptation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Action Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.adaptation.AdaptationPackage#getActionType()
 * @model
 * @generated
 */
public enum ActionType implements Enumerator {
	/**
	 * The '<em><b>SHOW HINT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHOW_HINT_VALUE
	 * @generated
	 * @ordered
	 */
	SHOW_HINT(0, "SHOW_HINT", "SHOW_HINT"),

	/**
	 * The '<em><b>CHANGE HINT LEVEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE_HINT_LEVEL_VALUE
	 * @generated
	 * @ordered
	 */
	CHANGE_HINT_LEVEL(1, "CHANGE_HINT_LEVEL", "CHANGE_HINT_LEVEL"),

	/**
	 * The '<em><b>REPEAT ACTIVITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPEAT_ACTIVITY_VALUE
	 * @generated
	 * @ordered
	 */
	REPEAT_ACTIVITY(2, "REPEAT_ACTIVITY", "REPEAT_ACTIVITY"),

	/**
	 * The '<em><b>SELECT REINFORCEMENT ACTIVITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELECT_REINFORCEMENT_ACTIVITY_VALUE
	 * @generated
	 * @ordered
	 */
	SELECT_REINFORCEMENT_ACTIVITY(3, "SELECT_REINFORCEMENT_ACTIVITY", "SELECT_REINFORCEMENT_ACTIVITY"),

	/**
	 * The '<em><b>ADVANCE TO NEXT CONCEPT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADVANCE_TO_NEXT_CONCEPT_VALUE
	 * @generated
	 * @ordered
	 */
	ADVANCE_TO_NEXT_CONCEPT(4, "ADVANCE_TO_NEXT_CONCEPT", "ADVANCE_TO_NEXT_CONCEPT"),

	/**
	 * The '<em><b>INCREASE DIFFICULTY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCREASE_DIFFICULTY_VALUE
	 * @generated
	 * @ordered
	 */
	INCREASE_DIFFICULTY(5, "INCREASE_DIFFICULTY", "INCREASE_DIFFICULTY"),

	/**
	 * The '<em><b>DECREASE DIFFICULTY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DECREASE_DIFFICULTY_VALUE
	 * @generated
	 * @ordered
	 */
	DECREASE_DIFFICULTY(6, "DECREASE_DIFFICULTY", "DECREASE_DIFFICULTY"),

	/**
	 * The '<em><b>SHOW CODE VIEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHOW_CODE_VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	SHOW_CODE_VIEW(7, "SHOW_CODE_VIEW", "SHOW_CODE_VIEW"),

	/**
	 * The '<em><b>HIDE CODE VIEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIDE_CODE_VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	HIDE_CODE_VIEW(8, "HIDE_CODE_VIEW", "HIDE_CODE_VIEW"),

	/**
	 * The '<em><b>CHANGE FEEDBACK STYLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE_FEEDBACK_STYLE_VALUE
	 * @generated
	 * @ordered
	 */
	CHANGE_FEEDBACK_STYLE(9, "CHANGE_FEEDBACK_STYLE", "CHANGE_FEEDBACK_STYLE");

	/**
	 * The '<em><b>SHOW HINT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHOW_HINT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHOW_HINT_VALUE = 0;

	/**
	 * The '<em><b>CHANGE HINT LEVEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE_HINT_LEVEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CHANGE_HINT_LEVEL_VALUE = 1;

	/**
	 * The '<em><b>REPEAT ACTIVITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPEAT_ACTIVITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REPEAT_ACTIVITY_VALUE = 2;

	/**
	 * The '<em><b>SELECT REINFORCEMENT ACTIVITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELECT_REINFORCEMENT_ACTIVITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SELECT_REINFORCEMENT_ACTIVITY_VALUE = 3;

	/**
	 * The '<em><b>ADVANCE TO NEXT CONCEPT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADVANCE_TO_NEXT_CONCEPT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ADVANCE_TO_NEXT_CONCEPT_VALUE = 4;

	/**
	 * The '<em><b>INCREASE DIFFICULTY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCREASE_DIFFICULTY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INCREASE_DIFFICULTY_VALUE = 5;

	/**
	 * The '<em><b>DECREASE DIFFICULTY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DECREASE_DIFFICULTY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DECREASE_DIFFICULTY_VALUE = 6;

	/**
	 * The '<em><b>SHOW CODE VIEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHOW_CODE_VIEW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHOW_CODE_VIEW_VALUE = 7;

	/**
	 * The '<em><b>HIDE CODE VIEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIDE_CODE_VIEW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HIDE_CODE_VIEW_VALUE = 8;

	/**
	 * The '<em><b>CHANGE FEEDBACK STYLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHANGE_FEEDBACK_STYLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CHANGE_FEEDBACK_STYLE_VALUE = 9;

	/**
	 * An array of all the '<em><b>Action Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ActionType[] VALUES_ARRAY =
		new ActionType[] {
			SHOW_HINT,
			CHANGE_HINT_LEVEL,
			REPEAT_ACTIVITY,
			SELECT_REINFORCEMENT_ACTIVITY,
			ADVANCE_TO_NEXT_CONCEPT,
			INCREASE_DIFFICULTY,
			DECREASE_DIFFICULTY,
			SHOW_CODE_VIEW,
			HIDE_CODE_VIEW,
			CHANGE_FEEDBACK_STYLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Action Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ActionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Action Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ActionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Action Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ActionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ActionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Action Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ActionType get(int value) {
		switch (value) {
			case SHOW_HINT_VALUE: return SHOW_HINT;
			case CHANGE_HINT_LEVEL_VALUE: return CHANGE_HINT_LEVEL;
			case REPEAT_ACTIVITY_VALUE: return REPEAT_ACTIVITY;
			case SELECT_REINFORCEMENT_ACTIVITY_VALUE: return SELECT_REINFORCEMENT_ACTIVITY;
			case ADVANCE_TO_NEXT_CONCEPT_VALUE: return ADVANCE_TO_NEXT_CONCEPT;
			case INCREASE_DIFFICULTY_VALUE: return INCREASE_DIFFICULTY;
			case DECREASE_DIFFICULTY_VALUE: return DECREASE_DIFFICULTY;
			case SHOW_CODE_VIEW_VALUE: return SHOW_CODE_VIEW;
			case HIDE_CODE_VIEW_VALUE: return HIDE_CODE_VIEW;
			case CHANGE_FEEDBACK_STYLE_VALUE: return CHANGE_FEEDBACK_STYLE;
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
	private ActionType(int value, String name, String literal) {
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
	
} //ActionType
