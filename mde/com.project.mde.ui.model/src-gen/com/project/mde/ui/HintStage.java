/**
 */
package com.project.mde.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Hint Stage</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiPackage#getHintStage()
 * @model
 * @generated
 */
public enum HintStage implements Enumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "NONE"),

	/**
	 * The '<em><b>SOCRATIC QUESTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOCRATIC_QUESTION_VALUE
	 * @generated
	 * @ordered
	 */
	SOCRATIC_QUESTION(1, "SOCRATIC_QUESTION", "SOCRATIC_QUESTION"),

	/**
	 * The '<em><b>CONCEPTUAL HINT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCEPTUAL_HINT_VALUE
	 * @generated
	 * @ordered
	 */
	CONCEPTUAL_HINT(2, "CONCEPTUAL_HINT", "CONCEPTUAL_HINT"),

	/**
	 * The '<em><b>ANALOGOUS EXAMPLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANALOGOUS_EXAMPLE_VALUE
	 * @generated
	 * @ordered
	 */
	ANALOGOUS_EXAMPLE(3, "ANALOGOUS_EXAMPLE", "ANALOGOUS_EXAMPLE"),

	/**
	 * The '<em><b>PARTIAL HELP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTIAL_HELP_VALUE
	 * @generated
	 * @ordered
	 */
	PARTIAL_HELP(4, "PARTIAL_HELP", "PARTIAL_HELP");

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>SOCRATIC QUESTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOCRATIC_QUESTION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SOCRATIC_QUESTION_VALUE = 1;

	/**
	 * The '<em><b>CONCEPTUAL HINT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCEPTUAL_HINT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONCEPTUAL_HINT_VALUE = 2;

	/**
	 * The '<em><b>ANALOGOUS EXAMPLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANALOGOUS_EXAMPLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ANALOGOUS_EXAMPLE_VALUE = 3;

	/**
	 * The '<em><b>PARTIAL HELP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTIAL_HELP
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARTIAL_HELP_VALUE = 4;

	/**
	 * An array of all the '<em><b>Hint Stage</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final HintStage[] VALUES_ARRAY =
		new HintStage[] {
			NONE,
			SOCRATIC_QUESTION,
			CONCEPTUAL_HINT,
			ANALOGOUS_EXAMPLE,
			PARTIAL_HELP,
		};

	/**
	 * A public read-only list of all the '<em><b>Hint Stage</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<HintStage> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Hint Stage</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static HintStage get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HintStage result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Hint Stage</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static HintStage getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HintStage result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Hint Stage</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static HintStage get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case SOCRATIC_QUESTION_VALUE: return SOCRATIC_QUESTION;
			case CONCEPTUAL_HINT_VALUE: return CONCEPTUAL_HINT;
			case ANALOGOUS_EXAMPLE_VALUE: return ANALOGOUS_EXAMPLE;
			case PARTIAL_HELP_VALUE: return PARTIAL_HELP;
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
	private HintStage(int value, String name, String literal) {
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
	
} //HintStage
