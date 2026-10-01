/**
 */
package com.project.mde.programming;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sensor Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Consulta de una condición abstracta del entorno, independiente de su representación visual.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.programming.SensorExpression#getSensor <em>Sensor</em>}</li>
 * </ul>
 *
 * @see com.project.mde.programming.ProgrammingPackage#getSensorExpression()
 * @model
 * @generated
 */
public interface SensorExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Sensor</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.programming.SensorKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Condición abstracta que será consultada al entorno.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sensor</em>' attribute.
	 * @see com.project.mde.programming.SensorKind
	 * @see #setSensor(SensorKind)
	 * @see com.project.mde.programming.ProgrammingPackage#getSensorExpression_Sensor()
	 * @model required="true"
	 * @generated
	 */
	SensorKind getSensor();

	/**
	 * Sets the value of the '{@link com.project.mde.programming.SensorExpression#getSensor <em>Sensor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sensor</em>' attribute.
	 * @see com.project.mde.programming.SensorKind
	 * @see #getSensor()
	 * @generated
	 */
	void setSensor(SensorKind value);

} // SensorExpression
