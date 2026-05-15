import React, { useEffect, useState } from 'react'
import { Button } from '@/components/ui/button'
import { toast } from "sonner"
import { Toaster } from "@/components/ui/sonner"
import { ArrowDown, ArrowUp, Check, ShoppingBag } from "lucide-react"
import { Link } from 'react-router-dom'

function Home() {
  const [allProducts, setAllProducts] = useState([]);
  const [categories, setCategories] = useState([])
  const [products, setProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("all")
  const [sort, setSort] = useState("id,asc")
  const [size, setSize] = useState(2)

  const PRODUCTS_API_URL = `${import.meta.env.VITE_API_URL}/products`
  const CATEGORIES_API_URL = `${import.meta.env.VITE_API_URL}/categories`

  useEffect(() => {
    fetch(CATEGORIES_API_URL)
      .then((response) => {
        if (!response.ok) {
          return [];
        }

        return response.json();
      })
      .then((json) => {

        if (Array.isArray(json)) {
          setCategories(json)
        }

      })
      .catch(() => {
        setCategories([])
      })
  }, [])

  useEffect(() => {
    fetch(PRODUCTS_API_URL)
      .then(res => res.json())
      .then(json => {

        // kui backend tagastab Page objekti
        const data = json.content ? json.content : json;

        setAllProducts(data)
        setProducts(data)
      })
  }, [selectedCategory, sort, size]);

  const sortAZ = () => {
    const sorted = [...products].sort((a, b) =>
      a.title.localeCompare(b.title)
    )
    setProducts(sorted)
  }

  const sortZA = () => {
    const sorted = [...products].sort((a, b) =>
      b.title.localeCompare(a.title)
    )
    setProducts(sorted)
  }

  const sortPriceIncreasing = () => {
    const sorted = [...products].sort((a, b) =>
      a.price - b.price
    )
    setProducts(sorted)
  }

  const sortPriceDecreasing = () => {
    const sorted = [...products].sort((a, b) =>
      b.price - a.price
    )
    setProducts(sorted)
  }

  const filterByCategory = (category) => {

    setSelectedCategory(category)

    if (category === "all") {
      setProducts(allProducts)
      return
    }

    const filtered = allProducts.filter(product =>
      product.category?.name === category ||
      product.category === category
    )

    setProducts(filtered)
  }

  const addToCart = (product) => {
    const cartLS = JSON.parse(localStorage.getItem("cart")) || [];
    cartLS.push(product);
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }

  return (
    <div className="flex flex-col gap-6 pt-4">
      <h1 className="text-xl font-semibold">React Storefront</h1>

      <div className="flex flex-wrap gap-2">
        <Button onClick={sortAZ} variant="outline">A-Z</Button>
        <Button onClick={sortZA} variant="outline">Z-A</Button>

        <Button onClick={sortPriceIncreasing} variant="outline">
          Price <ArrowUp />
        </Button>

        <Button onClick={sortPriceDecreasing} variant="outline">
          Price <ArrowDown />
        </Button>
      </div>

      <div className="flex items-center gap-2">
        <label htmlFor="category-filter">Choose category</label>

        <select onChange={(e) => filterByCategory(e.target.value)}>
          <option value="all">All</option>

          {categories.map(category =>
            <option key={category.id}>
              {category.name}
            </option>
          )}
        </select>
      </div>

      <div className="flex items-center gap-2">
        <label htmlFor="category-filter">Choose size</label>

        <select onChange={(e) => setSize(e.target.value)}>
          <option>2</option>
          <option>3</option>
        </select>
      </div>

      <div>{products.length} items currently in stock.</div>

      {products.map((product, index) =>
        <div
          key={product.id}
          className="grid w-full grid-cols-[2rem_100px_minmax(0,1fr)_auto] items-center gap-4 py-8"
        >
          <div className="text-right">{index + 1}.</div>

          <img
            className="w-[100px] h-[100px] object-cover"
            src={product.image}
            alt={product.description}
          />

          <div className="min-w-0">
            <div>{product.title}</div>
            <div>{product.price}€</div>
          </div>

          <div className="justify-self-end flex gap-2">
            <Button asChild variant="outline">
              <Link to={`/product/${product.id}`}>
                View product
              </Link>
            </Button>

            <Button
              size="icon"
              onClick={() => {
                addToCart(product)

                toast("Product has been added to the cart.", {
                  icon: <Check className="h-4 w-4" />,
                })
              }}
            >
              <ShoppingBag />
            </Button>
          </div>
        </div>
      )}

      <Toaster position="top-center" />
    </div>
  )
}

export default Home