/**
 */
package com.project.mde.ui.impl;

import com.project.mde.ui.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UiFactoryImpl extends EFactoryImpl implements UiFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UiFactory init() {
		try {
			UiFactory theUiFactory = (UiFactory)EPackage.Registry.INSTANCE.getEFactory(UiPackage.eNS_URI);
			if (theUiFactory != null) {
				return theUiFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UiFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UiFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UiPackage.TASK_AND_DOMAIN_MODEL: return createTaskAndDomainModel();
			case UiPackage.ABSTRACT_UI_MODEL: return createAbstractUIModel();
			case UiPackage.ABSTRACT_ELEMENT: return createAbstractElement();
			case UiPackage.CONCRETE_UI_MODEL: return createConcreteUIModel();
			case UiPackage.CONCRETE_ELEMENT: return createConcreteElement();
			case UiPackage.FINAL_UI_CONFIGURATION: return createFinalUIConfiguration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case UiPackage.ABSTRACT_KIND:
				return createAbstractKindFromString(eDataType, initialValue);
			case UiPackage.CONCRETE_KIND:
				return createConcreteKindFromString(eDataType, initialValue);
			case UiPackage.HINT_PANEL_MODE:
				return createHintPanelModeFromString(eDataType, initialValue);
			case UiPackage.FEEDBACK_DETAIL_LEVEL:
				return createFeedbackDetailLevelFromString(eDataType, initialValue);
			case UiPackage.ACTIVITY_LAYOUT:
				return createActivityLayoutFromString(eDataType, initialValue);
			case UiPackage.NAVIGATION_MODE:
				return createNavigationModeFromString(eDataType, initialValue);
			case UiPackage.DIFFICULTY_MODE:
				return createDifficultyModeFromString(eDataType, initialValue);
			case UiPackage.TUTOR_MODE:
				return createTutorModeFromString(eDataType, initialValue);
			case UiPackage.TRANSITION_MODE:
				return createTransitionModeFromString(eDataType, initialValue);
			case UiPackage.HINT_STAGE:
				return createHintStageFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case UiPackage.ABSTRACT_KIND:
				return convertAbstractKindToString(eDataType, instanceValue);
			case UiPackage.CONCRETE_KIND:
				return convertConcreteKindToString(eDataType, instanceValue);
			case UiPackage.HINT_PANEL_MODE:
				return convertHintPanelModeToString(eDataType, instanceValue);
			case UiPackage.FEEDBACK_DETAIL_LEVEL:
				return convertFeedbackDetailLevelToString(eDataType, instanceValue);
			case UiPackage.ACTIVITY_LAYOUT:
				return convertActivityLayoutToString(eDataType, instanceValue);
			case UiPackage.NAVIGATION_MODE:
				return convertNavigationModeToString(eDataType, instanceValue);
			case UiPackage.DIFFICULTY_MODE:
				return convertDifficultyModeToString(eDataType, instanceValue);
			case UiPackage.TUTOR_MODE:
				return convertTutorModeToString(eDataType, instanceValue);
			case UiPackage.TRANSITION_MODE:
				return convertTransitionModeToString(eDataType, instanceValue);
			case UiPackage.HINT_STAGE:
				return convertHintStageToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TaskAndDomainModel createTaskAndDomainModel() {
		TaskAndDomainModelImpl taskAndDomainModel = new TaskAndDomainModelImpl();
		return taskAndDomainModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractUIModel createAbstractUIModel() {
		AbstractUIModelImpl abstractUIModel = new AbstractUIModelImpl();
		return abstractUIModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractElement createAbstractElement() {
		AbstractElementImpl abstractElement = new AbstractElementImpl();
		return abstractElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConcreteUIModel createConcreteUIModel() {
		ConcreteUIModelImpl concreteUIModel = new ConcreteUIModelImpl();
		return concreteUIModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConcreteElement createConcreteElement() {
		ConcreteElementImpl concreteElement = new ConcreteElementImpl();
		return concreteElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FinalUIConfiguration createFinalUIConfiguration() {
		FinalUIConfigurationImpl finalUIConfiguration = new FinalUIConfigurationImpl();
		return finalUIConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractKind createAbstractKindFromString(EDataType eDataType, String initialValue) {
		AbstractKind result = AbstractKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAbstractKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConcreteKind createConcreteKindFromString(EDataType eDataType, String initialValue) {
		ConcreteKind result = ConcreteKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConcreteKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HintPanelMode createHintPanelModeFromString(EDataType eDataType, String initialValue) {
		HintPanelMode result = HintPanelMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHintPanelModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeedbackDetailLevel createFeedbackDetailLevelFromString(EDataType eDataType, String initialValue) {
		FeedbackDetailLevel result = FeedbackDetailLevel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFeedbackDetailLevelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityLayout createActivityLayoutFromString(EDataType eDataType, String initialValue) {
		ActivityLayout result = ActivityLayout.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActivityLayoutToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationMode createNavigationModeFromString(EDataType eDataType, String initialValue) {
		NavigationMode result = NavigationMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNavigationModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DifficultyMode createDifficultyModeFromString(EDataType eDataType, String initialValue) {
		DifficultyMode result = DifficultyMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDifficultyModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TutorMode createTutorModeFromString(EDataType eDataType, String initialValue) {
		TutorMode result = TutorMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTutorModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionMode createTransitionModeFromString(EDataType eDataType, String initialValue) {
		TransitionMode result = TransitionMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransitionModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HintStage createHintStageFromString(EDataType eDataType, String initialValue) {
		HintStage result = HintStage.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHintStageToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UiPackage getUiPackage() {
		return (UiPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UiPackage getPackage() {
		return UiPackage.eINSTANCE;
	}

} //UiFactoryImpl
