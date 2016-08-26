Ext.define('Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.CustomeUI', {
     "xtype": "customeUIView",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "gfb",
          "name": "gfb",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "gfb_textfield"
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_28101",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Project3.project3.web.projectthree.controller.encodecoboundedcontext.newdomain.CustomeUIController", "Project3.project3.shared.projectthree.viewmodel.encodecoboundedcontext.newdomain.CustomeUIViewModel", "Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.CustomeUIModel"],
     "viewModel": "CustomeUIViewModel",
     "controller": "CustomeUIController"
});