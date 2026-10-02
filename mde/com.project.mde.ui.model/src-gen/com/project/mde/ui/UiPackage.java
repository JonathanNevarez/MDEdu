/**
 */
package com.project.mde.ui;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.project.mde.ui.UiFactory
 * @model kind="package"
 * @generated
 */
public interface UiPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ui";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://mdedu.espoch.edu.ec/model/ui/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ui";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UiPackage eINSTANCE = com.project.mde.ui.impl.UiPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.project.mde.ui.impl.TaskAndDomainModelImpl <em>Task And Domain Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.impl.TaskAndDomainModelImpl
	 * @see com.project.mde.ui.impl.UiPackageImpl#getTaskAndDomainModel()
	 * @generated
	 */
	int TASK_AND_DOMAIN_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__ACTIVITY_ID = 0;

	/**
	 * The feature id for the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__CONCEPT_ID = 1;

	/**
	 * The feature id for the '<em><b>Requires Editor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR = 2;

	/**
	 * The feature id for the '<em><b>Requires Simulator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR = 3;

	/**
	 * The feature id for the '<em><b>Supports Code View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW = 4;

	/**
	 * The feature id for the '<em><b>Supports Hints</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS = 5;

	/**
	 * The feature id for the '<em><b>Supports Feedback</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK = 6;

	/**
	 * The feature id for the '<em><b>Supports Navigation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION = 7;

	/**
	 * The feature id for the '<em><b>Supports Tutor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR = 8;

	/**
	 * The number of structural features of the '<em>Task And Domain Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Task And Domain Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_AND_DOMAIN_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.impl.AbstractUIModelImpl <em>Abstract UI Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.impl.AbstractUIModelImpl
	 * @see com.project.mde.ui.impl.UiPackageImpl#getAbstractUIModel()
	 * @generated
	 */
	int ABSTRACT_UI_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_UI_MODEL__ACTIVITY_ID = 0;

	/**
	 * The feature id for the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_UI_MODEL__CONCEPT_ID = 1;

	/**
	 * The feature id for the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_UI_MODEL__ORIGIN_ID = 2;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_UI_MODEL__ELEMENTS = 3;

	/**
	 * The number of structural features of the '<em>Abstract UI Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_UI_MODEL_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Abstract UI Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_UI_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.impl.AbstractElementImpl <em>Abstract Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.impl.AbstractElementImpl
	 * @see com.project.mde.ui.impl.UiPackageImpl#getAbstractElement()
	 * @generated
	 */
	int ABSTRACT_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT__KIND = 0;

	/**
	 * The feature id for the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT__ORIGIN_ID = 1;

	/**
	 * The number of structural features of the '<em>Abstract Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Abstract Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.impl.ConcreteUIModelImpl <em>Concrete UI Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.impl.ConcreteUIModelImpl
	 * @see com.project.mde.ui.impl.UiPackageImpl#getConcreteUIModel()
	 * @generated
	 */
	int CONCRETE_UI_MODEL = 3;

	/**
	 * The feature id for the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL__ACTIVITY_ID = 0;

	/**
	 * The feature id for the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL__CONCEPT_ID = 1;

	/**
	 * The feature id for the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL__ORIGIN_ID = 2;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL__LAYOUT = 3;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL__ELEMENTS = 4;

	/**
	 * The number of structural features of the '<em>Concrete UI Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Concrete UI Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_UI_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.impl.ConcreteElementImpl <em>Concrete Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.impl.ConcreteElementImpl
	 * @see com.project.mde.ui.impl.UiPackageImpl#getConcreteElement()
	 * @generated
	 */
	int CONCRETE_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_ELEMENT__KIND = 0;

	/**
	 * The feature id for the '<em><b>Origin Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_ELEMENT__ORIGIN_ID = 1;

	/**
	 * The number of structural features of the '<em>Concrete Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Concrete Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCRETE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.impl.FinalUIConfigurationImpl <em>Final UI Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.impl.FinalUIConfigurationImpl
	 * @see com.project.mde.ui.impl.UiPackageImpl#getFinalUIConfiguration()
	 * @generated
	 */
	int FINAL_UI_CONFIGURATION = 5;

	/**
	 * The feature id for the '<em><b>Configuration Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION = 0;

	/**
	 * The feature id for the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__ACTIVITY_ID = 1;

	/**
	 * The feature id for the '<em><b>Show Code Panel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL = 2;

	/**
	 * The feature id for the '<em><b>Hint Panel Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__HINT_PANEL_MODE = 3;

	/**
	 * The feature id for the '<em><b>Feedback Detail Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL = 4;

	/**
	 * The feature id for the '<em><b>Activity Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT = 5;

	/**
	 * The feature id for the '<em><b>Enabled Assistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE = 6;

	/**
	 * The feature id for the '<em><b>Navigation Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__NAVIGATION_MODE = 7;

	/**
	 * The feature id for the '<em><b>Difficulty Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__DIFFICULTY_MODE = 8;

	/**
	 * The feature id for the '<em><b>Tutor Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__TUTOR_MODE = 9;

	/**
	 * The feature id for the '<em><b>Transition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__TRANSITION_MODE = 10;

	/**
	 * The feature id for the '<em><b>Next Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID = 11;

	/**
	 * The feature id for the '<em><b>Repeat Current Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY = 12;

	/**
	 * The feature id for the '<em><b>Hint Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__HINT_STAGE = 13;

	/**
	 * The feature id for the '<em><b>Feedback Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE = 14;

	/**
	 * The feature id for the '<em><b>Generated Code Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE = 15;

	/**
	 * The number of structural features of the '<em>Final UI Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION_FEATURE_COUNT = 16;

	/**
	 * The number of operations of the '<em>Final UI Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_UI_CONFIGURATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.AbstractKind <em>Abstract Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.AbstractKind
	 * @see com.project.mde.ui.impl.UiPackageImpl#getAbstractKind()
	 * @generated
	 */
	int ABSTRACT_KIND = 6;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.ConcreteKind <em>Concrete Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.ConcreteKind
	 * @see com.project.mde.ui.impl.UiPackageImpl#getConcreteKind()
	 * @generated
	 */
	int CONCRETE_KIND = 7;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.HintPanelMode <em>Hint Panel Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.HintPanelMode
	 * @see com.project.mde.ui.impl.UiPackageImpl#getHintPanelMode()
	 * @generated
	 */
	int HINT_PANEL_MODE = 8;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.FeedbackDetailLevel <em>Feedback Detail Level</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.FeedbackDetailLevel
	 * @see com.project.mde.ui.impl.UiPackageImpl#getFeedbackDetailLevel()
	 * @generated
	 */
	int FEEDBACK_DETAIL_LEVEL = 9;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.ActivityLayout <em>Activity Layout</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.ActivityLayout
	 * @see com.project.mde.ui.impl.UiPackageImpl#getActivityLayout()
	 * @generated
	 */
	int ACTIVITY_LAYOUT = 10;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.NavigationMode <em>Navigation Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.NavigationMode
	 * @see com.project.mde.ui.impl.UiPackageImpl#getNavigationMode()
	 * @generated
	 */
	int NAVIGATION_MODE = 11;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.DifficultyMode <em>Difficulty Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.DifficultyMode
	 * @see com.project.mde.ui.impl.UiPackageImpl#getDifficultyMode()
	 * @generated
	 */
	int DIFFICULTY_MODE = 12;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.TutorMode <em>Tutor Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.TutorMode
	 * @see com.project.mde.ui.impl.UiPackageImpl#getTutorMode()
	 * @generated
	 */
	int TUTOR_MODE = 13;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.TransitionMode <em>Transition Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.TransitionMode
	 * @see com.project.mde.ui.impl.UiPackageImpl#getTransitionMode()
	 * @generated
	 */
	int TRANSITION_MODE = 14;

	/**
	 * The meta object id for the '{@link com.project.mde.ui.HintStage <em>Hint Stage</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.ui.HintStage
	 * @see com.project.mde.ui.impl.UiPackageImpl#getHintStage()
	 * @generated
	 */
	int HINT_STAGE = 15;


	/**
	 * Returns the meta object for class '{@link com.project.mde.ui.TaskAndDomainModel <em>Task And Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task And Domain Model</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel
	 * @generated
	 */
	EClass getTaskAndDomainModel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#getActivityId <em>Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Id</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#getActivityId()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_ActivityId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#getConceptId <em>Concept Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concept Id</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#getConceptId()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_ConceptId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isRequiresEditor <em>Requires Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Editor</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isRequiresEditor()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_RequiresEditor();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isRequiresSimulator <em>Requires Simulator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Simulator</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isRequiresSimulator()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_RequiresSimulator();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsCodeView <em>Supports Code View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Code View</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isSupportsCodeView()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_SupportsCodeView();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsHints <em>Supports Hints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Hints</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isSupportsHints()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_SupportsHints();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsFeedback <em>Supports Feedback</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Feedback</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isSupportsFeedback()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_SupportsFeedback();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsNavigation <em>Supports Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Navigation</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isSupportsNavigation()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_SupportsNavigation();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.TaskAndDomainModel#isSupportsTutor <em>Supports Tutor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Tutor</em>'.
	 * @see com.project.mde.ui.TaskAndDomainModel#isSupportsTutor()
	 * @see #getTaskAndDomainModel()
	 * @generated
	 */
	EAttribute getTaskAndDomainModel_SupportsTutor();

	/**
	 * Returns the meta object for class '{@link com.project.mde.ui.AbstractUIModel <em>Abstract UI Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract UI Model</em>'.
	 * @see com.project.mde.ui.AbstractUIModel
	 * @generated
	 */
	EClass getAbstractUIModel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.AbstractUIModel#getActivityId <em>Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Id</em>'.
	 * @see com.project.mde.ui.AbstractUIModel#getActivityId()
	 * @see #getAbstractUIModel()
	 * @generated
	 */
	EAttribute getAbstractUIModel_ActivityId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.AbstractUIModel#getConceptId <em>Concept Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concept Id</em>'.
	 * @see com.project.mde.ui.AbstractUIModel#getConceptId()
	 * @see #getAbstractUIModel()
	 * @generated
	 */
	EAttribute getAbstractUIModel_ConceptId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.AbstractUIModel#getOriginId <em>Origin Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin Id</em>'.
	 * @see com.project.mde.ui.AbstractUIModel#getOriginId()
	 * @see #getAbstractUIModel()
	 * @generated
	 */
	EAttribute getAbstractUIModel_OriginId();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.ui.AbstractUIModel#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see com.project.mde.ui.AbstractUIModel#getElements()
	 * @see #getAbstractUIModel()
	 * @generated
	 */
	EReference getAbstractUIModel_Elements();

	/**
	 * Returns the meta object for class '{@link com.project.mde.ui.AbstractElement <em>Abstract Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Element</em>'.
	 * @see com.project.mde.ui.AbstractElement
	 * @generated
	 */
	EClass getAbstractElement();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.AbstractElement#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see com.project.mde.ui.AbstractElement#getKind()
	 * @see #getAbstractElement()
	 * @generated
	 */
	EAttribute getAbstractElement_Kind();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.AbstractElement#getOriginId <em>Origin Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin Id</em>'.
	 * @see com.project.mde.ui.AbstractElement#getOriginId()
	 * @see #getAbstractElement()
	 * @generated
	 */
	EAttribute getAbstractElement_OriginId();

	/**
	 * Returns the meta object for class '{@link com.project.mde.ui.ConcreteUIModel <em>Concrete UI Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concrete UI Model</em>'.
	 * @see com.project.mde.ui.ConcreteUIModel
	 * @generated
	 */
	EClass getConcreteUIModel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.ConcreteUIModel#getActivityId <em>Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Id</em>'.
	 * @see com.project.mde.ui.ConcreteUIModel#getActivityId()
	 * @see #getConcreteUIModel()
	 * @generated
	 */
	EAttribute getConcreteUIModel_ActivityId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.ConcreteUIModel#getConceptId <em>Concept Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concept Id</em>'.
	 * @see com.project.mde.ui.ConcreteUIModel#getConceptId()
	 * @see #getConcreteUIModel()
	 * @generated
	 */
	EAttribute getConcreteUIModel_ConceptId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.ConcreteUIModel#getOriginId <em>Origin Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin Id</em>'.
	 * @see com.project.mde.ui.ConcreteUIModel#getOriginId()
	 * @see #getConcreteUIModel()
	 * @generated
	 */
	EAttribute getConcreteUIModel_OriginId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.ConcreteUIModel#getLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Layout</em>'.
	 * @see com.project.mde.ui.ConcreteUIModel#getLayout()
	 * @see #getConcreteUIModel()
	 * @generated
	 */
	EAttribute getConcreteUIModel_Layout();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.ui.ConcreteUIModel#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see com.project.mde.ui.ConcreteUIModel#getElements()
	 * @see #getConcreteUIModel()
	 * @generated
	 */
	EReference getConcreteUIModel_Elements();

	/**
	 * Returns the meta object for class '{@link com.project.mde.ui.ConcreteElement <em>Concrete Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concrete Element</em>'.
	 * @see com.project.mde.ui.ConcreteElement
	 * @generated
	 */
	EClass getConcreteElement();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.ConcreteElement#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see com.project.mde.ui.ConcreteElement#getKind()
	 * @see #getConcreteElement()
	 * @generated
	 */
	EAttribute getConcreteElement_Kind();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.ConcreteElement#getOriginId <em>Origin Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin Id</em>'.
	 * @see com.project.mde.ui.ConcreteElement#getOriginId()
	 * @see #getConcreteElement()
	 * @generated
	 */
	EAttribute getConcreteElement_OriginId();

	/**
	 * Returns the meta object for class '{@link com.project.mde.ui.FinalUIConfiguration <em>Final UI Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final UI Configuration</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration
	 * @generated
	 */
	EClass getFinalUIConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getConfigurationVersion <em>Configuration Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Configuration Version</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getConfigurationVersion()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_ConfigurationVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getActivityId <em>Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Id</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getActivityId()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_ActivityId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#isShowCodePanel <em>Show Code Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Code Panel</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#isShowCodePanel()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_ShowCodePanel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getHintPanelMode <em>Hint Panel Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Panel Mode</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getHintPanelMode()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_HintPanelMode();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getFeedbackDetailLevel <em>Feedback Detail Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feedback Detail Level</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getFeedbackDetailLevel()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_FeedbackDetailLevel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getActivityLayout <em>Activity Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Layout</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getActivityLayout()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_ActivityLayout();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#isEnabledAssistance <em>Enabled Assistance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled Assistance</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#isEnabledAssistance()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_EnabledAssistance();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getNavigationMode <em>Navigation Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Navigation Mode</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getNavigationMode()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_NavigationMode();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getDifficultyMode <em>Difficulty Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Difficulty Mode</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getDifficultyMode()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_DifficultyMode();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getTutorMode <em>Tutor Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tutor Mode</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getTutorMode()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_TutorMode();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getTransitionMode <em>Transition Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transition Mode</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getTransitionMode()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_TransitionMode();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getNextActivityId <em>Next Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Next Activity Id</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getNextActivityId()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_NextActivityId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#isRepeatCurrentActivity <em>Repeat Current Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeat Current Activity</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#isRepeatCurrentActivity()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_RepeatCurrentActivity();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getHintStage <em>Hint Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Stage</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getHintStage()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_HintStage();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#getFeedbackMessage <em>Feedback Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feedback Message</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#getFeedbackMessage()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_FeedbackMessage();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.ui.FinalUIConfiguration#isGeneratedCodeVisible <em>Generated Code Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generated Code Visible</em>'.
	 * @see com.project.mde.ui.FinalUIConfiguration#isGeneratedCodeVisible()
	 * @see #getFinalUIConfiguration()
	 * @generated
	 */
	EAttribute getFinalUIConfiguration_GeneratedCodeVisible();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.AbstractKind <em>Abstract Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Abstract Kind</em>'.
	 * @see com.project.mde.ui.AbstractKind
	 * @generated
	 */
	EEnum getAbstractKind();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.ConcreteKind <em>Concrete Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Concrete Kind</em>'.
	 * @see com.project.mde.ui.ConcreteKind
	 * @generated
	 */
	EEnum getConcreteKind();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.HintPanelMode <em>Hint Panel Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Hint Panel Mode</em>'.
	 * @see com.project.mde.ui.HintPanelMode
	 * @generated
	 */
	EEnum getHintPanelMode();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.FeedbackDetailLevel <em>Feedback Detail Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Feedback Detail Level</em>'.
	 * @see com.project.mde.ui.FeedbackDetailLevel
	 * @generated
	 */
	EEnum getFeedbackDetailLevel();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.ActivityLayout <em>Activity Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Activity Layout</em>'.
	 * @see com.project.mde.ui.ActivityLayout
	 * @generated
	 */
	EEnum getActivityLayout();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.NavigationMode <em>Navigation Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Navigation Mode</em>'.
	 * @see com.project.mde.ui.NavigationMode
	 * @generated
	 */
	EEnum getNavigationMode();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.DifficultyMode <em>Difficulty Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Difficulty Mode</em>'.
	 * @see com.project.mde.ui.DifficultyMode
	 * @generated
	 */
	EEnum getDifficultyMode();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.TutorMode <em>Tutor Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tutor Mode</em>'.
	 * @see com.project.mde.ui.TutorMode
	 * @generated
	 */
	EEnum getTutorMode();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.TransitionMode <em>Transition Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transition Mode</em>'.
	 * @see com.project.mde.ui.TransitionMode
	 * @generated
	 */
	EEnum getTransitionMode();

	/**
	 * Returns the meta object for enum '{@link com.project.mde.ui.HintStage <em>Hint Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Hint Stage</em>'.
	 * @see com.project.mde.ui.HintStage
	 * @generated
	 */
	EEnum getHintStage();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UiFactory getUiFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.project.mde.ui.impl.TaskAndDomainModelImpl <em>Task And Domain Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.impl.TaskAndDomainModelImpl
		 * @see com.project.mde.ui.impl.UiPackageImpl#getTaskAndDomainModel()
		 * @generated
		 */
		EClass TASK_AND_DOMAIN_MODEL = eINSTANCE.getTaskAndDomainModel();

		/**
		 * The meta object literal for the '<em><b>Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__ACTIVITY_ID = eINSTANCE.getTaskAndDomainModel_ActivityId();

		/**
		 * The meta object literal for the '<em><b>Concept Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__CONCEPT_ID = eINSTANCE.getTaskAndDomainModel_ConceptId();

		/**
		 * The meta object literal for the '<em><b>Requires Editor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR = eINSTANCE.getTaskAndDomainModel_RequiresEditor();

		/**
		 * The meta object literal for the '<em><b>Requires Simulator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR = eINSTANCE.getTaskAndDomainModel_RequiresSimulator();

		/**
		 * The meta object literal for the '<em><b>Supports Code View</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW = eINSTANCE.getTaskAndDomainModel_SupportsCodeView();

		/**
		 * The meta object literal for the '<em><b>Supports Hints</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS = eINSTANCE.getTaskAndDomainModel_SupportsHints();

		/**
		 * The meta object literal for the '<em><b>Supports Feedback</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK = eINSTANCE.getTaskAndDomainModel_SupportsFeedback();

		/**
		 * The meta object literal for the '<em><b>Supports Navigation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION = eINSTANCE.getTaskAndDomainModel_SupportsNavigation();

		/**
		 * The meta object literal for the '<em><b>Supports Tutor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR = eINSTANCE.getTaskAndDomainModel_SupportsTutor();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.impl.AbstractUIModelImpl <em>Abstract UI Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.impl.AbstractUIModelImpl
		 * @see com.project.mde.ui.impl.UiPackageImpl#getAbstractUIModel()
		 * @generated
		 */
		EClass ABSTRACT_UI_MODEL = eINSTANCE.getAbstractUIModel();

		/**
		 * The meta object literal for the '<em><b>Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_UI_MODEL__ACTIVITY_ID = eINSTANCE.getAbstractUIModel_ActivityId();

		/**
		 * The meta object literal for the '<em><b>Concept Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_UI_MODEL__CONCEPT_ID = eINSTANCE.getAbstractUIModel_ConceptId();

		/**
		 * The meta object literal for the '<em><b>Origin Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_UI_MODEL__ORIGIN_ID = eINSTANCE.getAbstractUIModel_OriginId();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_UI_MODEL__ELEMENTS = eINSTANCE.getAbstractUIModel_Elements();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.impl.AbstractElementImpl <em>Abstract Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.impl.AbstractElementImpl
		 * @see com.project.mde.ui.impl.UiPackageImpl#getAbstractElement()
		 * @generated
		 */
		EClass ABSTRACT_ELEMENT = eINSTANCE.getAbstractElement();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ELEMENT__KIND = eINSTANCE.getAbstractElement_Kind();

		/**
		 * The meta object literal for the '<em><b>Origin Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ELEMENT__ORIGIN_ID = eINSTANCE.getAbstractElement_OriginId();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.impl.ConcreteUIModelImpl <em>Concrete UI Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.impl.ConcreteUIModelImpl
		 * @see com.project.mde.ui.impl.UiPackageImpl#getConcreteUIModel()
		 * @generated
		 */
		EClass CONCRETE_UI_MODEL = eINSTANCE.getConcreteUIModel();

		/**
		 * The meta object literal for the '<em><b>Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCRETE_UI_MODEL__ACTIVITY_ID = eINSTANCE.getConcreteUIModel_ActivityId();

		/**
		 * The meta object literal for the '<em><b>Concept Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCRETE_UI_MODEL__CONCEPT_ID = eINSTANCE.getConcreteUIModel_ConceptId();

		/**
		 * The meta object literal for the '<em><b>Origin Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCRETE_UI_MODEL__ORIGIN_ID = eINSTANCE.getConcreteUIModel_OriginId();

		/**
		 * The meta object literal for the '<em><b>Layout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCRETE_UI_MODEL__LAYOUT = eINSTANCE.getConcreteUIModel_Layout();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCRETE_UI_MODEL__ELEMENTS = eINSTANCE.getConcreteUIModel_Elements();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.impl.ConcreteElementImpl <em>Concrete Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.impl.ConcreteElementImpl
		 * @see com.project.mde.ui.impl.UiPackageImpl#getConcreteElement()
		 * @generated
		 */
		EClass CONCRETE_ELEMENT = eINSTANCE.getConcreteElement();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCRETE_ELEMENT__KIND = eINSTANCE.getConcreteElement_Kind();

		/**
		 * The meta object literal for the '<em><b>Origin Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCRETE_ELEMENT__ORIGIN_ID = eINSTANCE.getConcreteElement_OriginId();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.impl.FinalUIConfigurationImpl <em>Final UI Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.impl.FinalUIConfigurationImpl
		 * @see com.project.mde.ui.impl.UiPackageImpl#getFinalUIConfiguration()
		 * @generated
		 */
		EClass FINAL_UI_CONFIGURATION = eINSTANCE.getFinalUIConfiguration();

		/**
		 * The meta object literal for the '<em><b>Configuration Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION = eINSTANCE.getFinalUIConfiguration_ConfigurationVersion();

		/**
		 * The meta object literal for the '<em><b>Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__ACTIVITY_ID = eINSTANCE.getFinalUIConfiguration_ActivityId();

		/**
		 * The meta object literal for the '<em><b>Show Code Panel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL = eINSTANCE.getFinalUIConfiguration_ShowCodePanel();

		/**
		 * The meta object literal for the '<em><b>Hint Panel Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__HINT_PANEL_MODE = eINSTANCE.getFinalUIConfiguration_HintPanelMode();

		/**
		 * The meta object literal for the '<em><b>Feedback Detail Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL = eINSTANCE.getFinalUIConfiguration_FeedbackDetailLevel();

		/**
		 * The meta object literal for the '<em><b>Activity Layout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT = eINSTANCE.getFinalUIConfiguration_ActivityLayout();

		/**
		 * The meta object literal for the '<em><b>Enabled Assistance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE = eINSTANCE.getFinalUIConfiguration_EnabledAssistance();

		/**
		 * The meta object literal for the '<em><b>Navigation Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__NAVIGATION_MODE = eINSTANCE.getFinalUIConfiguration_NavigationMode();

		/**
		 * The meta object literal for the '<em><b>Difficulty Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__DIFFICULTY_MODE = eINSTANCE.getFinalUIConfiguration_DifficultyMode();

		/**
		 * The meta object literal for the '<em><b>Tutor Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__TUTOR_MODE = eINSTANCE.getFinalUIConfiguration_TutorMode();

		/**
		 * The meta object literal for the '<em><b>Transition Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__TRANSITION_MODE = eINSTANCE.getFinalUIConfiguration_TransitionMode();

		/**
		 * The meta object literal for the '<em><b>Next Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID = eINSTANCE.getFinalUIConfiguration_NextActivityId();

		/**
		 * The meta object literal for the '<em><b>Repeat Current Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY = eINSTANCE.getFinalUIConfiguration_RepeatCurrentActivity();

		/**
		 * The meta object literal for the '<em><b>Hint Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__HINT_STAGE = eINSTANCE.getFinalUIConfiguration_HintStage();

		/**
		 * The meta object literal for the '<em><b>Feedback Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE = eINSTANCE.getFinalUIConfiguration_FeedbackMessage();

		/**
		 * The meta object literal for the '<em><b>Generated Code Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE = eINSTANCE.getFinalUIConfiguration_GeneratedCodeVisible();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.AbstractKind <em>Abstract Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.AbstractKind
		 * @see com.project.mde.ui.impl.UiPackageImpl#getAbstractKind()
		 * @generated
		 */
		EEnum ABSTRACT_KIND = eINSTANCE.getAbstractKind();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.ConcreteKind <em>Concrete Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.ConcreteKind
		 * @see com.project.mde.ui.impl.UiPackageImpl#getConcreteKind()
		 * @generated
		 */
		EEnum CONCRETE_KIND = eINSTANCE.getConcreteKind();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.HintPanelMode <em>Hint Panel Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.HintPanelMode
		 * @see com.project.mde.ui.impl.UiPackageImpl#getHintPanelMode()
		 * @generated
		 */
		EEnum HINT_PANEL_MODE = eINSTANCE.getHintPanelMode();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.FeedbackDetailLevel <em>Feedback Detail Level</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.FeedbackDetailLevel
		 * @see com.project.mde.ui.impl.UiPackageImpl#getFeedbackDetailLevel()
		 * @generated
		 */
		EEnum FEEDBACK_DETAIL_LEVEL = eINSTANCE.getFeedbackDetailLevel();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.ActivityLayout <em>Activity Layout</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.ActivityLayout
		 * @see com.project.mde.ui.impl.UiPackageImpl#getActivityLayout()
		 * @generated
		 */
		EEnum ACTIVITY_LAYOUT = eINSTANCE.getActivityLayout();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.NavigationMode <em>Navigation Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.NavigationMode
		 * @see com.project.mde.ui.impl.UiPackageImpl#getNavigationMode()
		 * @generated
		 */
		EEnum NAVIGATION_MODE = eINSTANCE.getNavigationMode();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.DifficultyMode <em>Difficulty Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.DifficultyMode
		 * @see com.project.mde.ui.impl.UiPackageImpl#getDifficultyMode()
		 * @generated
		 */
		EEnum DIFFICULTY_MODE = eINSTANCE.getDifficultyMode();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.TutorMode <em>Tutor Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.TutorMode
		 * @see com.project.mde.ui.impl.UiPackageImpl#getTutorMode()
		 * @generated
		 */
		EEnum TUTOR_MODE = eINSTANCE.getTutorMode();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.TransitionMode <em>Transition Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.TransitionMode
		 * @see com.project.mde.ui.impl.UiPackageImpl#getTransitionMode()
		 * @generated
		 */
		EEnum TRANSITION_MODE = eINSTANCE.getTransitionMode();

		/**
		 * The meta object literal for the '{@link com.project.mde.ui.HintStage <em>Hint Stage</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.ui.HintStage
		 * @see com.project.mde.ui.impl.UiPackageImpl#getHintStage()
		 * @generated
		 */
		EEnum HINT_STAGE = eINSTANCE.getHintStage();

	}

} //UiPackage
