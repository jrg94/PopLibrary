function ModifyBGColor(id, newColor)
{
  var mElement = document.getElementById(id);
  mElement.style.backgroundColor = newColor;
}

function FillSearchList(id, numberOfItems)
{
  var mElement = document.getElementById(id);

    for (var i = 0; i < numberOfItems; i++) {
      var item = document.createElement('div');

      // DOM manipulation
      item.appendChild(document.createTextNode("cat"));

      mElement.appendChild(item);
    }

  return mElement;
}

FillSearchList('pl-library', 5);
