Inspiration = function () {
    /*****************************************************************************/
    // Canvas service functions
    const Context = function (name, id = "2d", attributes) {
        this.name = name;
        this.id = id;
        this.attributes = attributes;
        this.context = this.getContext();
    }

    Context.prototype.getCanvas = function () {
        if (typeof (this.canvas) == "undefined") {
            this.canvas = document.getElementById(this.name);
            if (!this.canvas) {
                return null;
            }
        }
        return this.canvas;
    }

    Context.prototype.getContext = function () {
        if (typeof (this.context) == "undefined") {
            if (this.getCanvas() == null) {
                console.log("Failed to retrieve the <canvas> element");
                return null;
            }
            console.log("Create new canvas for " + this.name);
            this.context = this.canvas.getContext(this.id, this.attributes);
            console.log("Create new " + this.id + " context for " + this.name);
        }
        return this.context;
    }
    /*****************************************************************************/

    /*****************************************************************************/
    // Functions for drawing simple lines
    function drawLine(context, fromX, fromY, toX, toY, color = "black", thickness = 1) {
        let ctx = context.getContext();
        ctx.beginPath();
        ctx.moveTo(fromX, fromY);
        ctx.lineTo(toX, toY);
        ctx.lineWidth = thickness;
        ctx.strokeStyle = _getRGBColor(color);
        ctx.stroke();
    }
    /*****************************************************************************/

    /*****************************************************************************/
    // Functions for drawing simple shapes
    function drawCircle(context, x, y, r, color = "green", alpha = 1.0) {
        let ctx = context.getContext();
        ctx.beginPath();
        ctx.arc(x, y, r, 0, Math.PI * 2, true);
        ctx.fillStyle = _getRGBAColor(color, alpha);
        ctx.fill();
    }

    function drawRectangle(context, x, y, width, height, color, alpha) {
        let ctx = context.getContext();
        ctx.fillStyle = _getRGBAColor(color, alpha);
        ctx.fillRect(x, y, width, height);
    }
    /*****************************************************************************/

    /*****************************************************************************/
    // Functions for drawing a board
    function drawLinks(context, board, color, thickness) {
        let length;
        let i;
        length = board.LINKS.length;
        for (i = 0; i < length; i++) {
            let str1 = board.LINKS[i].from;
            let a = str1.indexOf(";");
            let b = str1.lastIndexOf(";");
            let str2 = board.LINKS[i].to;
            let c = str2.indexOf(";");
            let d = str2.lastIndexOf(";");
            drawLine(context, str1.substr(0, a), str1.substr(a + 1, b - a - 1), str2.substr(0, c), str2.substr(c + 1, d - c - 1), color, thickness);
        }
    }

    function drawItems(context, board) {
        let length;
        let i;
        length = board.ITEMS.length;
        for(i =0; i < length; i++) {
            let str1 = board.ITEMS[i].position;
            let a = str1.indexOf(";");
            let b = str1.lastIndexOf(";");
            drawCircle(context,str1.substr(0,a),str1.substr(a+1,b-a-1),10,board.ITEMS[i].properties.color, 1.0);
        }
    }

    function drawRectangles(context, board, width = 100, height = 100) {
        let i;
        length = board.FIELDS.length;
        for (i = 0; i < length; i++) {
            let str = board.FIELDS[i].properties.position;
            let a = str.indexOf(";");
            let b = str.lastIndexOf(";");
            drawRectangle(context, Number(str.substr(0, a)) - width / 2, Number(str.substr(a + 1, b - a - 1)) - height / 2, width, height, board.FIELDS[i].properties.color, 1.0);
        }
    }

    function drawCircles(context, board, radius = 50) {
        let i;
        length = board.FIELDS.length;
        for (i = 0; i < length; i++) {
            let str = board.FIELDS[i].properties.position;
            let a = str.indexOf(";");
            let b = str.lastIndexOf(";");
            drawCircle(context, str.substr(0, a), str.substr(a + 1, b - a - 1), radius, board.FIELDS[i].properties.color, 1.0);
        }
    }
    /*****************************************************************************/

    /*****************************************************************************/
    // Returns the color code by its name.
    // Instead of the name, you can use the combination of "R,G,B"
    function _getRGBAColor(color, alpha) {
        let rgb = _getRGBColor(color);
        return "rgba(" + rgb.substr(4, rgb.length - 5) + "," + alpha + ")";
    }

    function _getRGBColor(color) {
        switch (color) {
            case "white" :
                return "rgb(255,255,255)";
            case "silver" :
                return "rgb(192,192,192)";
            case "gray" :
                return "rgb(128,128,128)";
            case "black" :
                return "rgb(0,0,0)";
            case "maroon" :
                return "rgb(128,0,0)";
            case "red" :
                return "rgb(255,0,0)";
            case "orange" :
                return "rgb(255,165,0)";
            case "yellow" :
                return "rgb(255,255,0)";
            case "olive" :
                return "rgb(128,128,0)";
            case "lime" :
                return "rgb(0,255,0)";
            case "green" :
                return "rgb(0,128,0)";
            case "aqua" :
                return "rgb(0,255,255)";
            case "blue" :
                return "rgb(0,0,255)";
            case "navy" :
                return "rgb(0,0,128)";
            case "teal" :
                return "rgb(0,128,128)";
            case "fuchsia" :
                return "rgb(255,0,255)";
            case "purple" :
                return "rgb(128,0,128)";
            default:
                return "rgb(" + color + ")";
        }
    }
    /*****************************************************************************/

    return {
        Context,
        drawLine,
        drawCircle,
        drawLinks,
        drawRectangle,
        drawRectangles,
        drawCircles,
        drawItems
    }
}();






