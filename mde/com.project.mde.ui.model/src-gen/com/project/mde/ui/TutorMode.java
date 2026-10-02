/**
 */
package com.project.mde.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Tutor Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiPackage#getTutorMode()
 * @model
 * @generated
 */
public enum TutorMode implements Enumerator {
	/**
	 * The '<em><b>HIDDEN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIDDEN_VALUE
	 * @generated
	 * @ordered
	 */
	HIDDEN(0, "HIDDEN", "HIDDEN"),

	/**
	 * The '<em><b>GUIDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GUIDE_VALUE
	 * @generated
	 * @ordered
	 */
	GUIDE(1, "GUIDE", "GUIDE"),

	/**
	 * The '<em><b>HINT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HINT_VALUE
	 * @generated
	 * @ordered
	 */
	HINT(2, "HINT", "HINT"),

	/**
	 * The '<em><b>FEEDBACK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEEDBACK_VALUE
	 * @generated
	 * @ordered
	 */
	FEEDBACK(3, "FEEDBACK", "FEEDBACK"),

	/**
	 * The '<em><b>SUCCESS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUCCESS_VALUE
	 * @generated
	 * @ordered
	 */
	SUCCESS(4, "SUCCESS", "SUCCESS");

	/**
	 * The '<em><b>HIDDEN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIDDEN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HIDDEN_VALUE = 0;

	/**
	 * The '<em><b>GUIDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GUIDE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GUIDE_VALUE = 1;

	/**
	 * The '<em><b>HINT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HINT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HINT_VALUE = 2;

	/**
	 * The '<em><b>FEEDBACK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEEDBACK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FEEDBACK_VALUE = 3;

	/**
	 * The '<em><b>SUCCESS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUCCESS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUCCESS_VALUE = 4;

	/**
	 * An array of all the '<em><b>Tutor Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TutorMode[] VALUES_ARRAY =
		new TutorMode[] {
			HIDDEN,
			GUIDE,
			HINT,
			FEEDBACK,
			SUCCESS,
		};

	/**
	 * A public read-only list of all the '<em><b>Tutor Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TutorMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Tutor Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TutorMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TutorMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tutor Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TutorMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TutorMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Tutor Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TutorMode get(int value) {
		switch (value) {
			case HIDDEN_VALUE: return HIDDEN;
			case GUIDE_VALUE: return GUIDE;
			case HINT_VALUE: return HINT;
			case FEEDBACK_VALUE: return FEEDBACK;
			case SUCCESS_VALUE: return SUCCESS;
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
	private TutorMode(int value, String name, String literal) {
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
	
} //TutorMode
