/**
 */
package com.project.mde.ui;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task And Domain Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isRequiresEditor <em>Requires Editor</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isRequiresSimulator <em>Requires Simulator</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isSupportsCodeView <em>Supports Code View</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isSupportsHints <em>Supports Hints</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isSupportsFeedback <em>Supports Feedback</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isSupportsNavigation <em>Supports Navigation</em>}</li>
 *   <li>{@link com.project.mde.ui.TaskAndDomainModel#isSupportsTutor <em>Supports Tutor</em>}</li>
 * </ul>
 *
 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel()
 * @model
 * @generated
 */
public interface TaskAndDomainModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Id</em>' attribute.
	 * @see #setActivityId(String)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_ActivityId()
	 * @model required="true"
	 * @generated
	 */
	String getActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#getActivityId <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Id</em>' attribute.
	 * @see #getActivityId()
	 * @generated
	 */
	void setActivityId(String value);

	/**
	 * Returns the value of the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concept Id</em>' attribute.
	 * @see #setConceptId(String)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_ConceptId()
	 * @model required="true"
	 * @generated
	 */
	String getConceptId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#getConceptId <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concept Id</em>' attribute.
	 * @see #getConceptId()
	 * @generated
	 */
	void setConceptId(String value);

	/**
	 * Returns the value of the '<em><b>Requires Editor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Editor</em>' attribute.
	 * @see #setRequiresEditor(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_RequiresEditor()
	 * @model required="true"
	 * @generated
	 */
	boolean isRequiresEditor();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isRequiresEditor <em>Requires Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requires Editor</em>' attribute.
	 * @see #isRequiresEditor()
	 * @generated
	 */
	void setRequiresEditor(boolean value);

	/**
	 * Returns the value of the '<em><b>Requires Simulator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Simulator</em>' attribute.
	 * @see #setRequiresSimulator(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_RequiresSimulator()
	 * @model required="true"
	 * @generated
	 */
	boolean isRequiresSimulator();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isRequiresSimulator <em>Requires Simulator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requires Simulator</em>' attribute.
	 * @see #isRequiresSimulator()
	 * @generated
	 */
	void setRequiresSimulator(boolean value);

	/**
	 * Returns the value of the '<em><b>Supports Code View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Code View</em>' attribute.
	 * @see #setSupportsCodeView(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_SupportsCodeView()
	 * @model required="true"
	 * @generated
	 */
	boolean isSupportsCodeView();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsCodeView <em>Supports Code View</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supports Code View</em>' attribute.
	 * @see #isSupportsCodeView()
	 * @generated
	 */
	void setSupportsCodeView(boolean value);

	/**
	 * Returns the value of the '<em><b>Supports Hints</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Hints</em>' attribute.
	 * @see #setSupportsHints(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_SupportsHints()
	 * @model required="true"
	 * @generated
	 */
	boolean isSupportsHints();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsHints <em>Supports Hints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supports Hints</em>' attribute.
	 * @see #isSupportsHints()
	 * @generated
	 */
	void setSupportsHints(boolean value);

	/**
	 * Returns the value of the '<em><b>Supports Feedback</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Feedback</em>' attribute.
	 * @see #setSupportsFeedback(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_SupportsFeedback()
	 * @model required="true"
	 * @generated
	 */
	boolean isSupportsFeedback();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsFeedback <em>Supports Feedback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supports Feedback</em>' attribute.
	 * @see #isSupportsFeedback()
	 * @generated
	 */
	void setSupportsFeedback(boolean value);

	/**
	 * Returns the value of the '<em><b>Supports Navigation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Navigation</em>' attribute.
	 * @see #setSupportsNavigation(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_SupportsNavigation()
	 * @model required="true"
	 * @generated
	 */
	boolean isSupportsNavigation();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsNavigation <em>Supports Navigation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supports Navigation</em>' attribute.
	 * @see #isSupportsNavigation()
	 * @generated
	 */
	void setSupportsNavigation(boolean value);

	/**
	 * Returns the value of the '<em><b>Supports Tutor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Tutor</em>' attribute.
	 * @see #setSupportsTutor(boolean)
	 * @see com.project.mde.ui.UiPackage#getTaskAndDomainModel_SupportsTutor()
	 * @model required="true"
	 * @generated
	 */
	boolean isSupportsTutor();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsTutor <em>Supports Tutor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supports Tutor</em>' attribute.
	 * @see #isSupportsTutor()
	 * @generated
	 */
	void setSupportsTutor(boolean value);

} // TaskAndDomainModel
