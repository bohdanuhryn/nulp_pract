(function(window, undefined) {
  var dictionary = {
    "6a61ad56-3bd0-4ce1-b4a9-667701859b4c": "PauseMenu",
    "d12245cc-1680-458d-89dd-4f0d7fb22724": "MainMenu",
    "3e7848be-d6a6-4dac-ab51-8267ede7083f": "GameMenu",
    "ac29551b-c1b5-4408-abab-d5747a12e9b6": "GameEnd",
    "6bedda1a-a7c9-4480-9966-f210546389b3": "SaveScore",
    "b5721fd3-df0d-4881-a1f7-ef1f5b423ae6": "Level",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "MenuTemplate",
    "557f684a-f924-4766-90c4-8f5edcde4357": "Empty"
  };

  var uriRE = /^(\/#)?(screens|templates|masters)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);