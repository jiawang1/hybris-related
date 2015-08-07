<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="./_ui/common/js/widget/inputList.css" />
        <link rel="stylesheet" type="text/css" href="./_ui/common/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="./_ui/common/css/relayout.css" />
        <link rel="stylesheet" type="text/css" href="./_ui/common/css/style.css" />
        <link rel="stylesheet" type="text/css" href="./_ui/common/css/jquery.dataTables.css" />
        <link rel="stylesheet" type="text/css" href="./_ui/common/css/dataTables.editor.min.css" />
        <link rel="stylesheet" type="text/css" href="./_ui/common/css/dataTables.tableTools.css" />
    </head>
<body>
      <div class="container">
   <div class="row">
    <div class="nav-root col-xs-4"></div>
    <div class="main-root col-xs-8"></div>
       </div>
    </div>
    <script type="application/javascript">
        var loadScriptHandler = function () {
            var loadScript = document.createElement("script");
            loadScript.src = "_ui/common/js/lib/require-2.1.19.js";
            loadScript.onload = function () {
                if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
                    require.config({
                        baseUrl: "_ui/common/js",
                        paths: {
                            jquery: 'lib/jquery-1.11.2', // resolve jquery double load issue
                            underscore: 'lib/underscore',
                            backbone: 'lib/backbone-1.1.2',
                            bootstrap: 'lib/bootstrap',
                            jstree:'lib/jstree',
                            datatables:'lib/jquery.dataTables'
                        },
                        shim: {
                            underscore: {
                                exports: '_'
                            },
                            backbone: {
                                deps: ["underscore", "jquery"],
                                exports: "backbone"
                            },
                            bootstrap:{
                                deps: ["jquery"],
                                exports: "bootstrap"
                            },
                            jstree:{
                                deps: ['jquery'],
                                exports: 'jstree'
                            },
                            datatables:{
                            	deps: ['jquery']
                            }
                        }
                    });
                    require(["applicationRouter"], function (router) {
                        var data = router;
                    });
                }
            };
            document.body.appendChild(loadScript);

        };
        if (window.addEventListener)
            window.addEventListener("DOMContentLoaded", loadScriptHandler, false);
        else if (window.attachEvent)
            window.attachEvent("onload", loadScriptHandler);
        else window.onload = loadScriptHandler;
    </script>
</body>

</html>