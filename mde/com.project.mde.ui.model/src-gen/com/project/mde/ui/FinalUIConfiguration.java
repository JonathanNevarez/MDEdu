/**
 */
package com.project.mde.ui;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Final UI Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getConfigurationVersion <em>Configuration Version</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#isShowCodePanel <em>Show Code Panel</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getHintPanelMode <em>Hint Panel Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getFeedbackDetailLevel <em>Feedback Detail Level</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getActivityLayout <em>Activity Layout</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#isEnabledAssistance <em>Enabled Assistance</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getNavigationMode <em>Navigation Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getDifficultyMode <em>Difficulty Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getTutorMode <em>Tutor Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getTransitionMode <em>Transition Mode</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getNextActivityId <em>Next Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#isRepeatCurrentActivity <em>Repeat Current Activity</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getHintStage <em>Hint Stage</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#getFeedbackMessage <em>Feedback Message</em>}</li>
 *   <li>{@link com.project.mde.ui.FinalUIConfiguration#isGeneratedCodeVisible <em>Generated Code Visible</em>}</li>
 * </ul>
 *
 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration()
 * @model
 * @generated
 */
public interface FinalUIConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Configuration Version</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Version</em>' attribute.
	 * @see #setConfigurationVersion(int)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_ConfigurationVersion()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getConfigurationVersion();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getConfigurationVersion <em>Configuration Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration Version</em>' attribute.
	 * @see #getConfigurationVersion()
	 * @generated
	 */
	void setConfigurationVersion(int value);

	/**
	 * Returns the value of the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Id</em>' attribute.
	 * @see #setActivityId(String)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_ActivityId()
	 * @model required="true"
	 * @generated
	 */
	String getActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getActivityId <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Id</em>' attribute.
	 * @see #getActivityId()
	 * @generated
	 */
	void setActivityId(String value);

	/**
	 * Returns the value of the '<em><b>Show Code Panel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Show Code Panel</em>' attribute.
	 * @see #setShowCodePanel(boolean)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_ShowCodePanel()
	 * @model required="true"
	 * @generated
	 */
	boolean isShowCodePanel();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#isShowCodePanel <em>Show Code Panel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Code Panel</em>' attribute.
	 * @see #isShowCodePanel()
	 * @generated
	 */
	void setShowCodePanel(boolean value);

	/**
	 * Returns the value of the '<em><b>Hint Panel Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.HintPanelMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Panel Mode</em>' attribute.
	 * @see com.project.mde.ui.HintPanelMode
	 * @see #setHintPanelMode(HintPanelMode)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_HintPanelMode()
	 * @model required="true"
	 * @generated
	 */
	HintPanelMode getHintPanelMode();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getHintPanelMode <em>Hint Panel Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint Panel Mode</em>' attribute.
	 * @see com.project.mde.ui.HintPanelMode
	 * @see #getHintPanelMode()
	 * @generated
	 */
	void setHintPanelMode(HintPanelMode value);

	/**
	 * Returns the value of the '<em><b>Feedback Detail Level</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.FeedbackDetailLevel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feedback Detail Level</em>' attribute.
	 * @see com.project.mde.ui.FeedbackDetailLevel
	 * @see #setFeedbackDetailLevel(FeedbackDetailLevel)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_FeedbackDetailLevel()
	 * @model required="true"
	 * @generated
	 */
	FeedbackDetailLevel getFeedbackDetailLevel();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getFeedbackDetailLevel <em>Feedback Detail Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feedback Detail Level</em>' attribute.
	 * @see com.project.mde.ui.FeedbackDetailLevel
	 * @see #getFeedbackDetailLevel()
	 * @generated
	 */
	void setFeedbackDetailLevel(FeedbackDetailLevel value);

	/**
	 * Returns the value of the '<em><b>Activity Layout</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.ActivityLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Layout</em>' attribute.
	 * @see com.project.mde.ui.ActivityLayout
	 * @see #setActivityLayout(ActivityLayout)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_ActivityLayout()
	 * @model required="true"
	 * @generated
	 */
	ActivityLayout getActivityLayout();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getActivityLayout <em>Activity Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Layout</em>' attribute.
	 * @see com.project.mde.ui.ActivityLayout
	 * @see #getActivityLayout()
	 * @generated
	 */
	void setActivityLayout(ActivityLayout value);

	/**
	 * Returns the value of the '<em><b>Enabled Assistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled Assistance</em>' attribute.
	 * @see #setEnabledAssistance(boolean)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_EnabledAssistance()
	 * @model required="true"
	 * @generated
	 */
	boolean isEnabledAssistance();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#isEnabledAssistance <em>Enabled Assistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled Assistance</em>' attribute.
	 * @see #isEnabledAssistance()
	 * @generated
	 */
	void setEnabledAssistance(boolean value);

	/**
	 * Returns the value of the '<em><b>Navigation Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.NavigationMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation Mode</em>' attribute.
	 * @see com.project.mde.ui.NavigationMode
	 * @see #setNavigationMode(NavigationMode)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_NavigationMode()
	 * @model required="true"
	 * @generated
	 */
	NavigationMode getNavigationMode();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getNavigationMode <em>Navigation Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Mode</em>' attribute.
	 * @see com.project.mde.ui.NavigationMode
	 * @see #getNavigationMode()
	 * @generated
	 */
	void setNavigationMode(NavigationMode value);

	/**
	 * Returns the value of the '<em><b>Difficulty Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.DifficultyMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Difficulty Mode</em>' attribute.
	 * @see com.project.mde.ui.DifficultyMode
	 * @see #setDifficultyMode(DifficultyMode)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_DifficultyMode()
	 * @model required="true"
	 * @generated
	 */
	DifficultyMode getDifficultyMode();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getDifficultyMode <em>Difficulty Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Difficulty Mode</em>' attribute.
	 * @see com.project.mde.ui.DifficultyMode
	 * @see #getDifficultyMode()
	 * @generated
	 */
	void setDifficultyMode(DifficultyMode value);

	/**
	 * Returns the value of the '<em><b>Tutor Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.TutorMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tutor Mode</em>' attribute.
	 * @see com.project.mde.ui.TutorMode
	 * @see #setTutorMode(TutorMode)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_TutorMode()
	 * @model required="true"
	 * @generated
	 */
	TutorMode getTutorMode();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getTutorMode <em>Tutor Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tutor Mode</em>' attribute.
	 * @see com.project.mde.ui.TutorMode
	 * @see #getTutorMode()
	 * @generated
	 */
	void setTutorMode(TutorMode value);

	/**
	 * Returns the value of the '<em><b>Transition Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.TransitionMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Mode</em>' attribute.
	 * @see com.project.mde.ui.TransitionMode
	 * @see #setTransitionMode(TransitionMode)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_TransitionMode()
	 * @model required="true"
	 * @generated
	 */
	TransitionMode getTransitionMode();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getTransitionMode <em>Transition Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Mode</em>' attribute.
	 * @see com.project.mde.ui.TransitionMode
	 * @see #getTransitionMode()
	 * @generated
	 */
	void setTransitionMode(TransitionMode value);

	/**
	 * Returns the value of the '<em><b>Next Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Activity Id</em>' attribute.
	 * @see #setNextActivityId(String)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_NextActivityId()
	 * @model
	 * @generated
	 */
	String getNextActivityId();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getNextActivityId <em>Next Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Activity Id</em>' attribute.
	 * @see #getNextActivityId()
	 * @generated
	 */
	void setNextActivityId(String value);

	/**
	 * Returns the value of the '<em><b>Repeat Current Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repeat Current Activity</em>' attribute.
	 * @see #setRepeatCurrentActivity(boolean)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_RepeatCurrentActivity()
	 * @model required="true"
	 * @generated
	 */
	boolean isRepeatCurrentActivity();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#isRepeatCurrentActivity <em>Repeat Current Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repeat Current Activity</em>' attribute.
	 * @see #isRepeatCurrentActivity()
	 * @generated
	 */
	void setRepeatCurrentActivity(boolean value);

	/**
	 * Returns the value of the '<em><b>Hint Stage</b></em>' attribute.
	 * The literals are from the enumeration {@link com.project.mde.ui.HintStage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint Stage</em>' attribute.
	 * @see com.project.mde.ui.HintStage
	 * @see #setHintStage(HintStage)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_HintStage()
	 * @model required="true"
	 * @generated
	 */
	HintStage getHintStage();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getHintStage <em>Hint Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint Stage</em>' attribute.
	 * @see com.project.mde.ui.HintStage
	 * @see #getHintStage()
	 * @generated
	 */
	void setHintStage(HintStage value);

	/**
	 * Returns the value of the '<em><b>Feedback Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feedback Message</em>' attribute.
	 * @see #setFeedbackMessage(String)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_FeedbackMessage()
	 * @model
	 * @generated
	 */
	String getFeedbackMessage();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#getFeedbackMessage <em>Feedback Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feedback Message</em>' attribute.
	 * @see #getFeedbackMessage()
	 * @generated
	 */
	void setFeedbackMessage(String value);

	/**
	 * Returns the value of the '<em><b>Generated Code Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated Code Visible</em>' attribute.
	 * @see #setGeneratedCodeVisible(boolean)
	 * @see com.project.mde.ui.UiPackage#getFinalUIConfiguration_GeneratedCodeVisible()
	 * @model required="true"
	 * @generated
	 */
	boolean isGeneratedCodeVisible();

	/**
	 * Sets the value of the '{@link com.project.mde.ui.FinalUIConfiguration#isGeneratedCodeVisible <em>Generated Code Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated Code Visible</em>' attribute.
	 * @see #isGeneratedCodeVisible()
	 * @generated
	 */
	void setGeneratedCodeVisible(boolean value);

} // FinalUIConfiguration
