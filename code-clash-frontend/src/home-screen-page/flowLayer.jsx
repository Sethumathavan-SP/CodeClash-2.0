export function FlowLayer( { header, desc } ) {
    return (
        <div className="flow-layers">
          <div >
            <h4>{header}</h4>
          </div>
          <div>
            <p>{desc}</p>
          </div>
        </div>
    );
}