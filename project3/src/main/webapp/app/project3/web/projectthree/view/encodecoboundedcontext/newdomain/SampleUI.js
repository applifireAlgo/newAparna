Ext.define('Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.SampleUI', {
     "xtype": "sampleUIView",
     "items": [{
          "xtype": "listViewBaseView",
          "name": "gh",
          "isListPanel": true,
          "autoScroll": true,
          "border": false,
          "layout": "column",
          "defaults": {
               "columnWidth": "1.0"
          },
          "templateConfig": {
               "url": "secure/Answer/findAll",
               "requestMethodType": "GET",
               "uiId": "D11F925B-154C-4E95-B026-F0A481E38A48",
               "uiClass": "Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.PartUI",
               "uiType": 2
          },
          "title": "ListPanel",
          "padding": 0,
          "margin": 5,
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "gh_panel",
          "dockedItems": []
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_28680",
     "dockedItems": [],
     "requires": ["Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.PartUI", "Project3.project3.web.projectthree.controller.encodecoboundedcontext.newdomain.SampleUIController", "Project3.project3.shared.projectthree.viewmodel.encodecoboundedcontext.newdomain.SampleUIViewModel", "Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.SampleUIModel", "Project3.view.fw.component.ListViewBaseView"],
     "extend": "Ext.form.Panel",
     "viewModel": "SampleUIViewModel",
     "controller": "SampleUIController"
});