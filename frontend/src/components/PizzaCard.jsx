export default function PizzaCard({ pizza, isDetail }) {
  return (
    <div className="pizza-card">
      <h3>{pizza.name}</h3>
      <p>Prezzo: €{pizza.price}</p>

      {isDetail && (
        <>
          <p>Ingredienti: {pizza.ingredients.map(i => i.name).join(', ')}</p>
          <p>Disponibilità: {pizza.available ? "Disponibile" : "Esaurita"}</p>
        </>
      )}
    </div>
  );
}
