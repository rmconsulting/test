controllers.controller('ChartCtrl', function($scope, Server) {
  $scope.chartObject = {};

  // $scope.chartObject.type = "BarChart"; //barra horizontal
  $scope.chartObject.type = "ColumnChart"; //barra vertical
  $scope.chartObject.data = {
    "cols": [
      { id: "t", label: "Topping", type: "string" },
      { id: "s", label: "Luminaria1", type: "number" }
    ], "rows": [
      {
        c: [
          { v: "dia 1" },
          { v: 13 },
        ]
      },
      {
        c: [
          { v: "dia 2" },
          { v: 6 }
        ]
      },
      {
        c: [
          { v: "dia 3" },
          { v: 12 },
        ]
      },
      {
        c: [
          { v: "dia 4" },
          { v: 20 },
        ]
      }
    ]
  };

  $scope.chartObject.options = {
    'title': 'Titulo'
  };

});
