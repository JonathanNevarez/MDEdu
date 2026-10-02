/**
 */
package com.project.mde.learning;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.StudentModel#getModelVersion <em>Model Version</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getLastUpdated <em>Last Updated</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getConcepts <em>Concepts</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getActivities <em>Activities</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getLearningObjectives <em>Learning Objectives</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getErrorPatterns <em>Error Patterns</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getConceptMasteries <em>Concept Masteries</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getAttempts <em>Attempts</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getHintUsages <em>Hint Usages</em>}</li>
 *   <li>{@link com.project.mde.learning.StudentModel#getProgress <em>Progress</em>}</li>
 * </ul>
 *
 * @see com.project.mde.learning.LearningPackage#getStudentModel()
 * @model
 * @generated
 */
public interface StudentModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Model Version</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Version</em>' attribute.
	 * @see #setModelVersion(int)
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_ModelVersion()
	 * @model default="1"
	 * @generated
	 */
	int getModelVersion();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.StudentModel#getModelVersion <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Version</em>' attribute.
	 * @see #getModelVersion()
	 * @generated
	 */
	void setModelVersion(int value);

	/**
	 * Returns the value of the '<em><b>Last Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Updated</em>' attribute.
	 * @see #setLastUpdated(Date)
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_LastUpdated()
	 * @model required="true"
	 * @generated
	 */
	Date getLastUpdated();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.StudentModel#getLastUpdated <em>Last Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Updated</em>' attribute.
	 * @see #getLastUpdated()
	 * @generated
	 */
	void setLastUpdated(Date value);

	/**
	 * Returns the value of the '<em><b>Student</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student</em>' containment reference.
	 * @see #setStudent(Student)
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_Student()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Student getStudent();

	/**
	 * Sets the value of the '{@link com.project.mde.learning.StudentModel#getStudent <em>Student</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Student</em>' containment reference.
	 * @see #getStudent()
	 * @generated
	 */
	void setStudent(Student value);

	/**
	 * Returns the value of the '<em><b>Concepts</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.Concept}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concepts</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_Concepts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Concept> getConcepts();

	/**
	 * Returns the value of the '<em><b>Activities</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.Activity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activities</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_Activities()
	 * @model containment="true"
	 * @generated
	 */
	EList<Activity> getActivities();

	/**
	 * Returns the value of the '<em><b>Learning Objectives</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.LearningObjective}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Learning Objectives</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_LearningObjectives()
	 * @model containment="true"
	 * @generated
	 */
	EList<LearningObjective> getLearningObjectives();

	/**
	 * Returns the value of the '<em><b>Error Patterns</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.ErrorPattern}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Patterns</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_ErrorPatterns()
	 * @model containment="true"
	 * @generated
	 */
	EList<ErrorPattern> getErrorPatterns();

	/**
	 * Returns the value of the '<em><b>Concept Masteries</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.ConceptMastery}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept Masteries</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_ConceptMasteries()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConceptMastery> getConceptMasteries();

	/**
	 * Returns the value of the '<em><b>Attempts</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.Attempt}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attempts</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_Attempts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attempt> getAttempts();

	/**
	 * Returns the value of the '<em><b>Hint Usages</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.HintUsage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Usages</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_HintUsages()
	 * @model containment="true"
	 * @generated
	 */
	EList<HintUsage> getHintUsages();

	/**
	 * Returns the value of the '<em><b>Progress</b></em>' containment reference list.
	 * The list contents are of type {@link com.project.mde.learning.Progress}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Progress</em>' containment reference list.
	 * @see com.project.mde.learning.LearningPackage#getStudentModel_Progress()
	 * @model containment="true"
	 * @generated
	 */
	EList<Progress> getProgress();

} // StudentModel
