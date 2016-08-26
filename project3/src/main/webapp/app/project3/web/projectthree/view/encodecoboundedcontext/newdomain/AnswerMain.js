Ext.define('Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.AnswerMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "answerMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "AnswerMainController",
     "restURL": "/Answer",
     "defaults": {
          "split": true
     },
     "requires": ["Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.AnswerModel", "Project3.project3.web.projectthree.controller.encodecoboundedcontext.newdomain.AnswerMainController", "Project3.project3.shared.projectthree.viewmodel.encodecoboundedcontext.newdomain.AnswerViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "displayName": "Answer",
               "name": "AnswerTreeContainer",
               "itemId": "AnswerTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "AnswerTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "xtype": "form",
                    "displayName": "Answer",
                    "name": "Answer",
                    "itemId": "AnswerForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "ansId",
                                   "itemId": "ansId",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "ansId",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "ansId<font color='red'> *<\/font>",
                                   "fieldId": "6ABB3A03-CB3B-4C47-8EA1-7150864B554C",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "ansId"
                              }, {
                                   "name": "ansName",
                                   "itemId": "ansName",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "ansName",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "ansName<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "AA068682-8553-4A14-9518-41476E2E657B",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "ansName",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "ansWei",
                                   "itemId": "ansWei",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "ansWei",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "ansWei",
                                   "fieldId": "F509D9C1-0972-49D0-BAC3-E6574BEE6E11",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "ansWei",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "versionId",
                                   "itemId": "versionId",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "versionId",
                                   "margin": "5 5 5 5",
                                   "value": "-1",
                                   "fieldLabel": "versionId",
                                   "fieldId": "F779968F-53F0-4F37-BB32-640FCBCAE146",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "Answer",
                    "title": "Details Grid",
                    "name": "AnswerGrid",
                    "itemId": "AnswerGrid",
                    "requires": [],
                    "columns": [{
                         "header": "ansId",
                         "dataIndex": "ansId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "ansName",
                         "dataIndex": "ansName",
                         "flex": 1
                    }, {
                         "header": "ansWei",
                         "dataIndex": "ansWei",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "xtype": "form",
               "displayName": "Answer",
               "name": "Answer",
               "itemId": "AnswerForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "ansId",
                              "itemId": "ansId",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "ansId",
                              "margin": "5 5 5 5",
                              "fieldLabel": "ansId<font color='red'> *<\/font>",
                              "fieldId": "6ABB3A03-CB3B-4C47-8EA1-7150864B554C",
                              "minLength": "1",
                              "maxLength": "256",
                              "hidden": true,
                              "value": "",
                              "bindable": "ansId"
                         }, {
                              "name": "ansName",
                              "itemId": "ansName",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "ansName",
                              "margin": "5 5 5 5",
                              "fieldLabel": "ansName<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "AA068682-8553-4A14-9518-41476E2E657B",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "ansName",
                              "columnWidth": 0.5
                         }, {
                              "name": "ansWei",
                              "itemId": "ansWei",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "ansWei",
                              "margin": "5 5 5 5",
                              "fieldLabel": "ansWei",
                              "fieldId": "F509D9C1-0972-49D0-BAC3-E6574BEE6E11",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "ansWei",
                              "columnWidth": 0.5
                         }, {
                              "name": "versionId",
                              "itemId": "versionId",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "versionId",
                              "margin": "5 5 5 5",
                              "value": "-1",
                              "fieldLabel": "versionId",
                              "fieldId": "F779968F-53F0-4F37-BB32-640FCBCAE146",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});