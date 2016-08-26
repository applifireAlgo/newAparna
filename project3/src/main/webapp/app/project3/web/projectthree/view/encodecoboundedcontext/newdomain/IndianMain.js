Ext.define('Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.IndianMain', {
     "xtype": "indianMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "IndianMainController",
     "restURL": "/Indian",
     "defaults": {
          "split": true
     },
     "requires": ["Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.IndianModel", "Project3.project3.web.projectthree.controller.encodecoboundedcontext.newdomain.IndianMainController", "Project3.project3.shared.projectthree.model.organization.locationmanagement.StateModel", "Project3.project3.shared.projectthree.viewmodel.encodecoboundedcontext.newdomain.IndianViewModel"],
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
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Indian",
               "name": "IndianTreeContainer",
               "itemId": "IndianTreeContainer",
               "restURL": "/Indian",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "IndianTree",
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
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Indian",
                    "title": "Indian",
                    "name": "Indian",
                    "itemId": "IndianForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "fromState",
                         "itemId": "fromState",
                         "xtype": "customcombobox",
                         "customWidgetType": "vdCombo",
                         "displayName": "fromState",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "customStore": {
                              "model": "Project3.project3.shared.projectthree.model.organization.locationmanagement.StateModel",
                              "autoLoad": true,
                              "autoSync": true,
                              "sorters": [{
                                   "property": "primaryDisplay",
                                   "direction": "ASC"
                              }],
                              "proxy": {
                                   "type": "ajax",
                                   "url": "secure/State/findAll",
                                   "actionMethods": {
                                        "read": "GET"
                                   },
                                   "headers": {
                                        "Content-Type": "application/json"
                                   },
                                   "extraParams": {},
                                   "reader": {
                                        "type": "json",
                                        "rootProperty": "response.data"
                                   }
                              }
                         },
                         "allowBlank": false,
                         "fieldLabel": "fromState<font color='red'> *<\/font>",
                         "fieldId": "13812607-97FC-48D1-B391-0500A85269E5",
                         "restURL": "State",
                         "bindable": "fromState",
                         "columnWidth": 0.5
                    }, {
                         "name": "foodType",
                         "itemId": "foodType",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "foodType",
                         "margin": "5 5 5 5",
                         "fieldLabel": "foodType<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "F89EC42B-88D4-49B1-BC98-6F8B0B3A2DF7",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "foodType",
                         "columnWidth": 0.5
                    }, {
                         "name": "foodId",
                         "itemId": "foodId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "foodId",
                         "margin": "5 5 5 5",
                         "fieldLabel": "foodId<font color='red'> *<\/font>",
                         "fieldId": "36D51155-1A70-45A9-AB44-7496C01BA8C6",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "foodId"
                    }, {
                         "name": "foodName",
                         "itemId": "foodName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "foodName",
                         "margin": "5 5 5 5",
                         "fieldLabel": "foodName<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "09A0D07A-9561-4ECF-B959-6E1902E9504A",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "foodName",
                         "columnWidth": 0.5
                    }, {
                         "name": "cuisineName",
                         "itemId": "cuisineName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "cuisineName",
                         "margin": "5 5 5 5",
                         "fieldLabel": "cuisineName<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "C3AA9566-7EE3-4EE2-9958-A3EE9C5E1B3B",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "cuisineName",
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
                         "fieldId": "2165B633-9023-47F4-A352-EE7C4089FF1F",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 584,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 584,
                              "customId": 864
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 584,
                              "customId": 865,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 584,
                              "customId": 866,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Indian",
                    "title": "Details Grid",
                    "name": "IndianGrid",
                    "itemId": "IndianGrid",
                    "restURL": "/Indian",
                    "columns": [{
                         "header": "fromState",
                         "dataIndex": "fromState",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "foodType",
                         "dataIndex": "foodType",
                         "flex": 1
                    }, {
                         "header": "foodId",
                         "dataIndex": "foodId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "foodName",
                         "dataIndex": "foodName",
                         "flex": 1
                    }, {
                         "header": "cuisineName",
                         "dataIndex": "cuisineName",
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
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Indian",
               "title": "Indian",
               "name": "Indian",
               "itemId": "IndianForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "fromState",
                    "itemId": "fromState",
                    "xtype": "customcombobox",
                    "customWidgetType": "vdCombo",
                    "displayName": "fromState",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "customStore": {
                         "model": "Project3.project3.shared.projectthree.model.organization.locationmanagement.StateModel",
                         "autoLoad": true,
                         "autoSync": true,
                         "sorters": [{
                              "property": "primaryDisplay",
                              "direction": "ASC"
                         }],
                         "proxy": {
                              "type": "ajax",
                              "url": "secure/State/findAll",
                              "actionMethods": {
                                   "read": "GET"
                              },
                              "headers": {
                                   "Content-Type": "application/json"
                              },
                              "extraParams": {},
                              "reader": {
                                   "type": "json",
                                   "rootProperty": "response.data"
                              }
                         }
                    },
                    "allowBlank": false,
                    "fieldLabel": "fromState<font color='red'> *<\/font>",
                    "fieldId": "13812607-97FC-48D1-B391-0500A85269E5",
                    "restURL": "State",
                    "bindable": "fromState",
                    "columnWidth": 0.5
               }, {
                    "name": "foodType",
                    "itemId": "foodType",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "foodType",
                    "margin": "5 5 5 5",
                    "fieldLabel": "foodType<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "F89EC42B-88D4-49B1-BC98-6F8B0B3A2DF7",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "foodType",
                    "columnWidth": 0.5
               }, {
                    "name": "foodId",
                    "itemId": "foodId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "foodId",
                    "margin": "5 5 5 5",
                    "fieldLabel": "foodId<font color='red'> *<\/font>",
                    "fieldId": "36D51155-1A70-45A9-AB44-7496C01BA8C6",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "foodId"
               }, {
                    "name": "foodName",
                    "itemId": "foodName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "foodName",
                    "margin": "5 5 5 5",
                    "fieldLabel": "foodName<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "09A0D07A-9561-4ECF-B959-6E1902E9504A",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "foodName",
                    "columnWidth": 0.5
               }, {
                    "name": "cuisineName",
                    "itemId": "cuisineName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "cuisineName",
                    "margin": "5 5 5 5",
                    "fieldLabel": "cuisineName<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "C3AA9566-7EE3-4EE2-9958-A3EE9C5E1B3B",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "cuisineName",
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
                    "fieldId": "2165B633-9023-47F4-A352-EE7C4089FF1F",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 584,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 584,
                         "customId": 864
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 584,
                         "customId": 865,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 584,
                         "customId": 866,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});