/**
 */
package com.project.mde.adaptation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Hint Level</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.project.mde.adaptation.AdaptationPackage#getHintLevel()
 * @model
 * @generated
 */
public enum HintLevel implements Enumerator {
	/**
	 * The '<em><b>CONCEPTUAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCEPTUAL_VALUE
	 * @generated
	 * @ordered
	 */
	CONCEPTUAL(0, "CONCEPTUAL", "CONCEPTUAL"),

	/**
	 * The '<em><b>GUIDED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GUIDED_VALUE
	 * @generated
	 * @ordered
	 */
	GUIDED(1, "GUIDED", "GUIDED"),

	/**
	 * The '<em><b>DIRECT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT_VALUE
	 * @generated
	 * @ordered
	 */
	DIRECT(2, "DIRECT", "DIRECT");

	/**
	 * The '<em><b>CONCEPTUAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCEPTUAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONCEPTUAL_VALUE = 0;

	/**
	 * The '<em><b>GUIDED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GUIDED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GUIDED_VALUE = 1;

	/**
	 * The '<em><b>DIRECT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIRECT_VALUE = 2;

	/**
	 * An array of all the '<em><b>Hint Level</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final HintLevel[] VALUES_ARRAY =
		new HintLevel[] {
			CONCEPTUAL,
			GUIDED,
			DIRECT,
		};

	/**
	 * A public read-only list of all the '<em><b>Hint Level</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<HintLevel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Hint Level</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static HintLevel get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HintLevel result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Hint Level</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static HintLevel getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			HintLevel result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Hint Level</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static HintLevel get(int value) {
		switch (value) {
			case CONCEPTUAL_VALUE: return CONCEPTUAL;
			case GUIDED_VALUE: return GUIDED;
			case DIRECT_VALUE: return DIRECT;
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
	private HintLevel(int value, String name, String literal) {
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
	
} //HintLevel
