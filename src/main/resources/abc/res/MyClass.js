class MyClass {
    static somethingStatic() {
      return "hello"
    }

    constructor(foo, bar) {
        this._foo = foo
        this._bar = bar
    }

    displayName() {
        return this._foo.toString() + '-' + this._bar.toString()
    }

    get Foo() {
        return this._foo
    }
    set Foo(foo) {
        this._foo = foo
    }

    get Bar() {
        return this._bar
    }
    set Bar(bar) {
        this._bar = bar
    }
}