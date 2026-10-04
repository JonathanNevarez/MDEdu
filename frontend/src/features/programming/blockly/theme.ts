import * as Blockly from 'blockly/core';
// Darker faces keep white Blockly text readable. Category accents match the shell tokens.
export const blockColors = {sequence:'#176BD0', variable:'#8134C7', condition:'#A85B00', loop:'#087F47', value:'#3B609C'};
export function blockStyle(type:string){
 if (/variable|assignment/.test(type)) return 'variable';
 if (/if|comparison|boolean_expression|sensor/.test(type)) return 'condition';
 if (/repeat|while|start/.test(type)) return 'loop';
 if (/literal/.test(type)) return 'value';
 return 'sequence';
}
export const educationalTheme = Blockly.Theme.defineTheme('mdedu', {
 name:'mdedu', base:Blockly.Themes.Classic,
 blockStyles:Object.fromEntries(Object.entries(blockColors).map(([key,colour])=>[key,{colourPrimary:colour}])),
 categoryStyles:Object.fromEntries(Object.entries(blockColors).map(([key,colour])=>[key,{colour}])),
 componentStyles:{workspaceBackgroundColour:'#F6FBFF',toolboxBackgroundColour:'#FFFFFF',toolboxForegroundColour:'#112D66',flyoutBackgroundColour:'#E9F5FF',flyoutForegroundColour:'#112D66',flyoutOpacity:1,scrollbarColour:'#86B5D6',insertionMarkerColour:'#153D88',insertionMarkerOpacity:.3},
 fontStyle:{family:'Tahoma, Arial, sans-serif',size:14,weight:'bold'},startHats:true,
});
export const editorOptions: Blockly.BlocklyOptions = {
 theme:educationalTheme,renderer:'geras',rendererOverrides:{FIELD_TEXT_FONTSIZE:14,FIELD_BORDER_RECT_X_PADDING:7},
 grid:{spacing:24,length:2,colour:'#D2E7F9',snap:false},
 zoom:{controls:true,wheel:false,startScale:1.05,minScale:.55,maxScale:1.8},
 scrollbars:true,trashcan:true,media:'/blockly-media/',sounds:false,
};
