describe("adding a todo item",function(){
    it("calls the callback with the result", function() {
        var pippo;

        runs(function () {
            pippo = "pluto";
        });

        waitsFor(function () {
           return pippo != undefined;
        });
    });



    it("works", function() {
        expect(0).toEqual(0);
    });
});