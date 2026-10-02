/**
 */
package com.project.mde.ui.impl;

import com.project.mde.ui.AbstractElement;
import com.project.mde.ui.AbstractKind;
import com.project.mde.ui.AbstractUIModel;
import com.project.mde.ui.ActivityLayout;
import com.project.mde.ui.ConcreteElement;
import com.project.mde.ui.ConcreteKind;
import com.project.mde.ui.ConcreteUIModel;
import com.project.mde.ui.DifficultyMode;
import com.project.mde.ui.FeedbackDetailLevel;
import com.project.mde.ui.FinalUIConfiguration;
import com.project.mde.ui.HintPanelMode;
import com.project.mde.ui.HintStage;
import com.project.mde.ui.NavigationMode;
import com.project.mde.ui.TaskAndDomainModel;
import com.project.mde.ui.TransitionMode;
import com.project.mde.ui.TutorMode;
import com.project.mde.ui.UiFactory;
import com.project.mde.ui.UiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UiPackageImpl extends EPackageImpl implements UiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskAndDomainModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractUIModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concreteUIModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concreteElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finalUIConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum abstractKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum concreteKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum hintPanelModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum feedbackDetailLevelEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum activityLayoutEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum navigationModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum difficultyModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tutorModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transitionModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum hintStageEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.project.mde.ui.UiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UiPackageImpl() {
		super(eNS_URI, UiFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link UiPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UiPackage init() {
		if (isInited) return (UiPackage)EPackage.Registry.INSTANCE.getEPackage(UiPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredUiPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		UiPackageImpl theUiPackage = registeredUiPackage instanceof UiPackageImpl ? (UiPackageImpl)registeredUiPackage : new UiPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theUiPackage.createPackageContents();

		// Initialize created meta-data
		theUiPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUiPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UiPackage.eNS_URI, theUiPackage);
		return theUiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTaskAndDomainModel() {
		return taskAndDomainModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_ActivityId() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_ConceptId() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_RequiresEditor() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_RequiresSimulator() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_SupportsCodeView() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_SupportsHints() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_SupportsFeedback() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_SupportsNavigation() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskAndDomainModel_SupportsTutor() {
		return (EAttribute)taskAndDomainModelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractUIModel() {
		return abstractUIModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractUIModel_ActivityId() {
		return (EAttribute)abstractUIModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractUIModel_ConceptId() {
		return (EAttribute)abstractUIModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractUIModel_OriginId() {
		return (EAttribute)abstractUIModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractUIModel_Elements() {
		return (EReference)abstractUIModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractElement() {
		return abstractElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractElement_Kind() {
		return (EAttribute)abstractElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractElement_OriginId() {
		return (EAttribute)abstractElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConcreteUIModel() {
		return concreteUIModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcreteUIModel_ActivityId() {
		return (EAttribute)concreteUIModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcreteUIModel_ConceptId() {
		return (EAttribute)concreteUIModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcreteUIModel_OriginId() {
		return (EAttribute)concreteUIModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcreteUIModel_Layout() {
		return (EAttribute)concreteUIModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcreteUIModel_Elements() {
		return (EReference)concreteUIModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConcreteElement() {
		return concreteElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcreteElement_Kind() {
		return (EAttribute)concreteElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConcreteElement_OriginId() {
		return (EAttribute)concreteElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFinalUIConfiguration() {
		return finalUIConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_ConfigurationVersion() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_ActivityId() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_ShowCodePanel() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_HintPanelMode() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_FeedbackDetailLevel() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_ActivityLayout() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_EnabledAssistance() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_NavigationMode() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_DifficultyMode() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_TutorMode() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_TransitionMode() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_NextActivityId() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_RepeatCurrentActivity() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_HintStage() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_FeedbackMessage() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFinalUIConfiguration_GeneratedCodeVisible() {
		return (EAttribute)finalUIConfigurationEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getAbstractKind() {
		return abstractKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getConcreteKind() {
		return concreteKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getHintPanelMode() {
		return hintPanelModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getFeedbackDetailLevel() {
		return feedbackDetailLevelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getActivityLayout() {
		return activityLayoutEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNavigationMode() {
		return navigationModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getDifficultyMode() {
		return difficultyModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTutorMode() {
		return tutorModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTransitionMode() {
		return transitionModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getHintStage() {
		return hintStageEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UiFactory getUiFactory() {
		return (UiFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		taskAndDomainModelEClass = createEClass(TASK_AND_DOMAIN_MODEL);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__ACTIVITY_ID);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__CONCEPT_ID);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION);
		createEAttribute(taskAndDomainModelEClass, TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR);

		abstractUIModelEClass = createEClass(ABSTRACT_UI_MODEL);
		createEAttribute(abstractUIModelEClass, ABSTRACT_UI_MODEL__ACTIVITY_ID);
		createEAttribute(abstractUIModelEClass, ABSTRACT_UI_MODEL__CONCEPT_ID);
		createEAttribute(abstractUIModelEClass, ABSTRACT_UI_MODEL__ORIGIN_ID);
		createEReference(abstractUIModelEClass, ABSTRACT_UI_MODEL__ELEMENTS);

		abstractElementEClass = createEClass(ABSTRACT_ELEMENT);
		createEAttribute(abstractElementEClass, ABSTRACT_ELEMENT__KIND);
		createEAttribute(abstractElementEClass, ABSTRACT_ELEMENT__ORIGIN_ID);

		concreteUIModelEClass = createEClass(CONCRETE_UI_MODEL);
		createEAttribute(concreteUIModelEClass, CONCRETE_UI_MODEL__ACTIVITY_ID);
		createEAttribute(concreteUIModelEClass, CONCRETE_UI_MODEL__CONCEPT_ID);
		createEAttribute(concreteUIModelEClass, CONCRETE_UI_MODEL__ORIGIN_ID);
		createEAttribute(concreteUIModelEClass, CONCRETE_UI_MODEL__LAYOUT);
		createEReference(concreteUIModelEClass, CONCRETE_UI_MODEL__ELEMENTS);

		concreteElementEClass = createEClass(CONCRETE_ELEMENT);
		createEAttribute(concreteElementEClass, CONCRETE_ELEMENT__KIND);
		createEAttribute(concreteElementEClass, CONCRETE_ELEMENT__ORIGIN_ID);

		finalUIConfigurationEClass = createEClass(FINAL_UI_CONFIGURATION);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__CONFIGURATION_VERSION);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__ACTIVITY_ID);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__SHOW_CODE_PANEL);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__HINT_PANEL_MODE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__FEEDBACK_DETAIL_LEVEL);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__ACTIVITY_LAYOUT);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__ENABLED_ASSISTANCE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__NAVIGATION_MODE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__DIFFICULTY_MODE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__TUTOR_MODE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__TRANSITION_MODE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__NEXT_ACTIVITY_ID);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__REPEAT_CURRENT_ACTIVITY);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__HINT_STAGE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__FEEDBACK_MESSAGE);
		createEAttribute(finalUIConfigurationEClass, FINAL_UI_CONFIGURATION__GENERATED_CODE_VISIBLE);

		// Create enums
		abstractKindEEnum = createEEnum(ABSTRACT_KIND);
		concreteKindEEnum = createEEnum(CONCRETE_KIND);
		hintPanelModeEEnum = createEEnum(HINT_PANEL_MODE);
		feedbackDetailLevelEEnum = createEEnum(FEEDBACK_DETAIL_LEVEL);
		activityLayoutEEnum = createEEnum(ACTIVITY_LAYOUT);
		navigationModeEEnum = createEEnum(NAVIGATION_MODE);
		difficultyModeEEnum = createEEnum(DIFFICULTY_MODE);
		tutorModeEEnum = createEEnum(TUTOR_MODE);
		transitionModeEEnum = createEEnum(TRANSITION_MODE);
		hintStageEEnum = createEEnum(HINT_STAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(taskAndDomainModelEClass, TaskAndDomainModel.class, "TaskAndDomainModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskAndDomainModel_ActivityId(), ecorePackage.getEString(), "activityId", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_ConceptId(), ecorePackage.getEString(), "conceptId", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_RequiresEditor(), ecorePackage.getEBoolean(), "requiresEditor", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_RequiresSimulator(), ecorePackage.getEBoolean(), "requiresSimulator", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_SupportsCodeView(), ecorePackage.getEBoolean(), "supportsCodeView", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_SupportsHints(), ecorePackage.getEBoolean(), "supportsHints", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_SupportsFeedback(), ecorePackage.getEBoolean(), "supportsFeedback", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_SupportsNavigation(), ecorePackage.getEBoolean(), "supportsNavigation", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskAndDomainModel_SupportsTutor(), ecorePackage.getEBoolean(), "supportsTutor", null, 1, 1, TaskAndDomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractUIModelEClass, AbstractUIModel.class, "AbstractUIModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractUIModel_ActivityId(), ecorePackage.getEString(), "activityId", null, 1, 1, AbstractUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractUIModel_ConceptId(), ecorePackage.getEString(), "conceptId", null, 1, 1, AbstractUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractUIModel_OriginId(), ecorePackage.getEString(), "originId", null, 1, 1, AbstractUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractUIModel_Elements(), this.getAbstractElement(), null, "elements", null, 0, -1, AbstractUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractElementEClass, AbstractElement.class, "AbstractElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractElement_Kind(), this.getAbstractKind(), "kind", null, 1, 1, AbstractElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractElement_OriginId(), ecorePackage.getEString(), "originId", null, 1, 1, AbstractElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(concreteUIModelEClass, ConcreteUIModel.class, "ConcreteUIModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConcreteUIModel_ActivityId(), ecorePackage.getEString(), "activityId", null, 1, 1, ConcreteUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcreteUIModel_ConceptId(), ecorePackage.getEString(), "conceptId", null, 1, 1, ConcreteUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcreteUIModel_OriginId(), ecorePackage.getEString(), "originId", null, 1, 1, ConcreteUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcreteUIModel_Layout(), this.getActivityLayout(), "layout", null, 1, 1, ConcreteUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcreteUIModel_Elements(), this.getConcreteElement(), null, "elements", null, 0, -1, ConcreteUIModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(concreteElementEClass, ConcreteElement.class, "ConcreteElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConcreteElement_Kind(), this.getConcreteKind(), "kind", null, 1, 1, ConcreteElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConcreteElement_OriginId(), ecorePackage.getEString(), "originId", null, 1, 1, ConcreteElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(finalUIConfigurationEClass, FinalUIConfiguration.class, "FinalUIConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFinalUIConfiguration_ConfigurationVersion(), ecorePackage.getEInt(), "configurationVersion", "1", 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_ActivityId(), ecorePackage.getEString(), "activityId", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_ShowCodePanel(), ecorePackage.getEBoolean(), "showCodePanel", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_HintPanelMode(), this.getHintPanelMode(), "hintPanelMode", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_FeedbackDetailLevel(), this.getFeedbackDetailLevel(), "feedbackDetailLevel", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_ActivityLayout(), this.getActivityLayout(), "activityLayout", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_EnabledAssistance(), ecorePackage.getEBoolean(), "enabledAssistance", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_NavigationMode(), this.getNavigationMode(), "navigationMode", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_DifficultyMode(), this.getDifficultyMode(), "difficultyMode", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_TutorMode(), this.getTutorMode(), "tutorMode", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_TransitionMode(), this.getTransitionMode(), "transitionMode", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_NextActivityId(), ecorePackage.getEString(), "nextActivityId", null, 0, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_RepeatCurrentActivity(), ecorePackage.getEBoolean(), "repeatCurrentActivity", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_HintStage(), this.getHintStage(), "hintStage", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_FeedbackMessage(), ecorePackage.getEString(), "feedbackMessage", null, 0, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFinalUIConfiguration_GeneratedCodeVisible(), ecorePackage.getEBoolean(), "generatedCodeVisible", null, 1, 1, FinalUIConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(abstractKindEEnum, AbstractKind.class, "AbstractKind");
		addEEnumLiteral(abstractKindEEnum, AbstractKind.WORKSPACE);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.SIMULATION_AREA);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.INSTRUCTION_AREA);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.FEEDBACK_AREA);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.HINT_AREA);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.NAVIGATION_AREA);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.CODE_AREA);
		addEEnumLiteral(abstractKindEEnum, AbstractKind.TUTOR_AREA);

		initEEnum(concreteKindEEnum, ConcreteKind.class, "ConcreteKind");
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.BLOCKLY_EDITOR);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.GRIDWORLD_VIEW);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.INSTRUCTION_PANEL);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.FEEDBACK_PANEL);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.HINT_PANEL);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.NAVIGATION_PANEL);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.CODE_PANEL);
		addEEnumLiteral(concreteKindEEnum, ConcreteKind.LUMA_TUTOR);

		initEEnum(hintPanelModeEEnum, HintPanelMode.class, "HintPanelMode");
		addEEnumLiteral(hintPanelModeEEnum, HintPanelMode.HIDDEN);
		addEEnumLiteral(hintPanelModeEEnum, HintPanelMode.COMPACT);
		addEEnumLiteral(hintPanelModeEEnum, HintPanelMode.EXPANDED);

		initEEnum(feedbackDetailLevelEEnum, FeedbackDetailLevel.class, "FeedbackDetailLevel");
		addEEnumLiteral(feedbackDetailLevelEEnum, FeedbackDetailLevel.STANDARD);
		addEEnumLiteral(feedbackDetailLevelEEnum, FeedbackDetailLevel.MINIMAL);
		addEEnumLiteral(feedbackDetailLevelEEnum, FeedbackDetailLevel.DETAILED);

		initEEnum(activityLayoutEEnum, ActivityLayout.class, "ActivityLayout");
		addEEnumLiteral(activityLayoutEEnum, ActivityLayout.STANDARD);
		addEEnumLiteral(activityLayoutEEnum, ActivityLayout.ASSISTED);
		addEEnumLiteral(activityLayoutEEnum, ActivityLayout.FOCUSED);

		initEEnum(navigationModeEEnum, NavigationMode.class, "NavigationMode");
		addEEnumLiteral(navigationModeEEnum, NavigationMode.STAY);
		addEEnumLiteral(navigationModeEEnum, NavigationMode.REPEAT);
		addEEnumLiteral(navigationModeEEnum, NavigationMode.ADVANCE);

		initEEnum(difficultyModeEEnum, DifficultyMode.class, "DifficultyMode");
		addEEnumLiteral(difficultyModeEEnum, DifficultyMode.STANDARD);
		addEEnumLiteral(difficultyModeEEnum, DifficultyMode.REDUCED);
		addEEnumLiteral(difficultyModeEEnum, DifficultyMode.INCREASED);

		initEEnum(tutorModeEEnum, TutorMode.class, "TutorMode");
		addEEnumLiteral(tutorModeEEnum, TutorMode.HIDDEN);
		addEEnumLiteral(tutorModeEEnum, TutorMode.GUIDE);
		addEEnumLiteral(tutorModeEEnum, TutorMode.HINT);
		addEEnumLiteral(tutorModeEEnum, TutorMode.FEEDBACK);
		addEEnumLiteral(tutorModeEEnum, TutorMode.SUCCESS);

		initEEnum(transitionModeEEnum, TransitionMode.class, "TransitionMode");
		addEEnumLiteral(transitionModeEEnum, TransitionMode.NONE);
		addEEnumLiteral(transitionModeEEnum, TransitionMode.SUBTLE);
		addEEnumLiteral(transitionModeEEnum, TransitionMode.NOTICEABLE);

		initEEnum(hintStageEEnum, HintStage.class, "HintStage");
		addEEnumLiteral(hintStageEEnum, HintStage.NONE);
		addEEnumLiteral(hintStageEEnum, HintStage.SOCRATIC_QUESTION);
		addEEnumLiteral(hintStageEEnum, HintStage.CONCEPTUAL_HINT);
		addEEnumLiteral(hintStageEEnum, HintStage.ANALOGOUS_EXAMPLE);
		addEEnumLiteral(hintStageEEnum, HintStage.PARTIAL_HELP);

		// Create resource
		createResource(eNS_URI);
	}

} //UiPackageImpl
