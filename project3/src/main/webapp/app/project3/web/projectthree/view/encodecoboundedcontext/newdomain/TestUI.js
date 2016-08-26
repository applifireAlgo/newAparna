Ext.define('Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.TestUI', {
     "xtype": "testUIView",
     "items": [{
          "xtype": "checkbox",
          "fieldLabel": "CheckBox",
          "name": "df",
          "bindable": "df",
          "margin": 5,
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "df_checkbox"
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_33976",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Project3.project3.web.projectthree.controller.encodecoboundedcontext.newdomain.TestUIController", "Project3.project3.shared.projectthree.viewmodel.encodecoboundedcontext.newdomain.TestUIViewModel", "Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.TestUIModel"],
     "viewModel": "TestUIViewModel",
     "controller": "TestUIController"
});